package io.github.golok56.data.repository.league

import android.database.SQLException
import io.github.golok56.data.entities.LeagueData
import io.github.golok56.data.mapper.LeagueDataToEntityMapper
import io.github.golok56.data.mapper.LeagueEntityToDataMapper
import io.github.golok56.domain.entities.LeagueEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class LeagueRepositoryTest {
    private lateinit var leagueRepository: LeagueRepository
    private lateinit var memoryDataStore: LeagueMemoryDataStore
    private lateinit var remoteDataStore: LeagueRemoteDataStore
    private lateinit var leagueDataToEntityMapper: LeagueDataToEntityMapper
    private lateinit var leagueEntityToDataMapper: LeagueEntityToDataMapper

    @Before
    fun setUp() {
        memoryDataStore = mock(LeagueMemoryDataStore::class.java)
        remoteDataStore = mock(LeagueRemoteDataStore::class.java)
        leagueDataToEntityMapper = LeagueDataToEntityMapper()
        leagueEntityToDataMapper = LeagueEntityToDataMapper()

        leagueRepository = LeagueRepository(
            memoryDataStore,
            remoteDataStore,
            leagueDataToEntityMapper,
            leagueEntityToDataMapper
        )
    }

    @Test
    fun getAll_shouldReturnFromMemoryIfMemoryIsNotEmpty() {
        val res = mutableListOf(mock(LeagueData::class.java), mock(LeagueData::class.java))
        `when`(memoryDataStore.getAll(anyString())).thenReturn(res)

        val leagues = leagueRepository.getAll(anyString())
        assertEquals(transformToEntity(res), leagues)
        assertEquals(2, leagues.size)
    }

    @Test
    fun getAll_shouldReturnFromRemoteIfMemoryIsEmpty() {
        val memoryRes = mutableListOf<LeagueData>()
        `when`(memoryDataStore.getAll(anyString())).thenReturn(memoryRes)

        val remoteRes = mutableListOf(mock(LeagueData::class.java), mock(LeagueData::class.java))
        `when`(remoteDataStore.getAll(anyString())).thenReturn(remoteRes)

        val leagues = leagueRepository.getAll(anyString())
        assertEquals(transformToEntity(remoteRes), leagues)
        assertEquals(2, leagues.size)
    }

    @Test
    fun get_shouldReturnFromMemoryIfMemoryIsNotEmpty() {
        val res = mock(LeagueData::class.java)
        `when`(res.id).thenReturn("Memory")

        `when`(memoryDataStore.get(anyString())).thenReturn(res)

        val league = leagueRepository.get(anyString())
        assertEquals(leagueDataToEntityMapper.map(res), league)
        assertEquals("Memory", league.id)
    }

    @Test
    fun get_shouldReturnFromRemoteIfMemoryIsEmpty() {
        val res = mock(LeagueData::class.java)
        `when`(res.id).thenReturn("Remote")

        `when`(memoryDataStore.get(anyString())).thenReturn(null)
        `when`(remoteDataStore.get(anyString())).thenReturn(res)

        val league = leagueRepository.get(anyString())
        assertEquals(leagueDataToEntityMapper.map(res), league)
        assertEquals("Remote", league.id)
    }

    @Test
    fun save_shouldReturnTrueWhenSuccess() {
        val league = mock(LeagueEntity::class.java)

        assertEquals(true, leagueRepository.save(league))
    }

    @Test(expected = SQLException::class)
    fun save_shouldThrowExceptionWhenError() {
        val league = mock(LeagueEntity::class.java)

        `when`(memoryDataStore.save(leagueEntityToDataMapper.map(league)))
            .thenThrow(SQLException::class.java)

        leagueRepository.save(league)
    }

    private fun transformToEntity(leagueData: MutableList<LeagueData>) = leagueData.map {
        leagueDataToEntityMapper.map(it)
    }.toMutableList()
}