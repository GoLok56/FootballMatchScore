package io.github.golok56.data.repository.team

import android.database.SQLException
import io.github.golok56.data.entities.TeamData
import io.github.golok56.data.mapper.TeamEntityToDataMapper
import io.github.golok56.data.mapper.TeamDataToEntityMapper
import io.github.golok56.domain.entities.TeamEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TeamRepositoryTest{
    private lateinit var teamRepository: TeamRepository
    private lateinit var memoryDataStore: TeamMemoryDataStore
    private lateinit var remoteDataStore: TeamRemoteDataStore
    private lateinit var teamDataToEntityMapper: TeamDataToEntityMapper
    private lateinit var teamEntityToDataMapper: TeamEntityToDataMapper

    @Before
    fun setUp() {
        memoryDataStore = Mockito.mock(TeamMemoryDataStore::class.java)
        remoteDataStore = Mockito.mock(TeamRemoteDataStore::class.java)
        teamDataToEntityMapper = TeamDataToEntityMapper()
        teamEntityToDataMapper = TeamEntityToDataMapper()

        teamRepository = TeamRepository(
            memoryDataStore,
            remoteDataStore,
            teamDataToEntityMapper,
            teamEntityToDataMapper
        )
    }

    @Test
    fun getAll_shouldReturnFromMemoryIfMemoryIsNotEmpty() {
        val res = mutableListOf(
            Mockito.mock(TeamData::class.java),
            Mockito.mock(TeamData::class.java)
        )
        Mockito.`when`(memoryDataStore.getAll(Mockito.anyString())).thenReturn(res)

        val teams = teamRepository.getAll(Mockito.anyString())
        assertEquals(transformToEntity(res), teams)
        assertEquals(2, teams.size)
    }

    @Test
    fun getAll_shouldReturnFromRemoteIfMemoryIsEmpty() {
        val memoryRes = mutableListOf<TeamData>()
        Mockito.`when`(memoryDataStore.getAll(Mockito.anyString())).thenReturn(memoryRes)

        val remoteRes = mutableListOf(
            Mockito.mock(TeamData::class.java),
            Mockito.mock(TeamData::class.java)
        )
        Mockito.`when`(remoteDataStore.getAll(Mockito.anyString())).thenReturn(remoteRes)

        val teams = teamRepository.getAll(Mockito.anyString())
        assertEquals(transformToEntity(remoteRes), teams)
        assertEquals(2, teams.size)
    }

    @Test
    fun get_shouldReturnFromMemoryIfMemoryIsNotEmpty() {
        val res = Mockito.mock(TeamData::class.java)
        Mockito.`when`(res.id).thenReturn("Memory")

        Mockito.`when`(memoryDataStore.get(Mockito.anyString())).thenReturn(res)

        val team = teamRepository.get(Mockito.anyString())
        assertEquals(teamDataToEntityMapper.map(res), team)
        assertEquals("Memory", team?.id)
    }

    @Test
    fun get_shouldReturnFromRemoteIfMemoryIsEmpty() {
        val res = Mockito.mock(TeamData::class.java)
        Mockito.`when`(res.id).thenReturn("Remote")

        Mockito.`when`(memoryDataStore.get(Mockito.anyString())).thenReturn(null)
        Mockito.`when`(remoteDataStore.get(Mockito.anyString())).thenReturn(res)

        val team = teamRepository.get(Mockito.anyString())
        assertEquals(teamDataToEntityMapper.map(res), team)
        assertEquals("Remote", team?.id)
    }

    @Test
    fun save_shouldReturnTrueWhenSuccess() {
        val team = Mockito.mock(TeamEntity::class.java)

        assertEquals(true, teamRepository.save(team))
    }

    @Test(expected = SQLException::class)
    fun save_shouldThrowExceptionWhenError() {
        val team = Mockito.mock(TeamEntity::class.java)

        Mockito.`when`(memoryDataStore.save(teamEntityToDataMapper.map(team)))
            .thenThrow(SQLException::class.java)

        teamRepository.save(team)
    }

    private fun transformToEntity(leagueData: MutableList<TeamData>) = leagueData.map {
        teamDataToEntityMapper.map(it)
    }.toMutableList()
}