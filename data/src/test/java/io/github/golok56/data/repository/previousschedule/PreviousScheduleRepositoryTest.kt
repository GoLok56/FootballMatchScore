package io.github.golok56.data.repository.previousschedule

import android.database.SQLException
import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.data.mapper.ScheduleDataToEntityMapper
import io.github.golok56.data.mapper.ScheduleEntityToDataMapper
import io.github.golok56.domain.entities.ScheduleEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class PreviousScheduleRepositoryTest {
    private lateinit var scheduleRepository: PreviousScheduleRepository
    private lateinit var memoryDataStore: PreviousScheduleMemoryDataStore
    private lateinit var remoteDataStore: PreviousScheduleRemoteDataStore
    private lateinit var scheduleDataToEntityMapper: ScheduleDataToEntityMapper
    private lateinit var scheduleEntityToDataMapper: ScheduleEntityToDataMapper

    @Before
    fun setUp() {
        memoryDataStore = Mockito.mock(PreviousScheduleMemoryDataStore::class.java)
        remoteDataStore = Mockito.mock(PreviousScheduleRemoteDataStore::class.java)
        scheduleDataToEntityMapper = ScheduleDataToEntityMapper()
        scheduleEntityToDataMapper = ScheduleEntityToDataMapper()

        scheduleRepository = PreviousScheduleRepository(
            memoryDataStore,
            remoteDataStore,
            scheduleDataToEntityMapper,
            scheduleEntityToDataMapper
        )
    }

    @Test
    fun getAll_shouldReturnFromMemoryIfMemoryIsNotEmpty() {
        val res = mutableListOf(
            Mockito.mock(ScheduleData::class.java),
            Mockito.mock(ScheduleData::class.java)
        )
        Mockito.`when`(memoryDataStore.getAll(Mockito.anyString())).thenReturn(res)

        val schedules = scheduleRepository.getAll(Mockito.anyString())
        assertEquals(transformToEntity(res), schedules)
        assertEquals(2, schedules.size)
    }

    @Test
    fun getAll_shouldReturnFromRemoteIfMemoryIsEmpty() {
        val memoryRes = mutableListOf<ScheduleData>()
        Mockito.`when`(memoryDataStore.getAll(Mockito.anyString())).thenReturn(memoryRes)

        val remoteRes = mutableListOf(
            Mockito.mock(ScheduleData::class.java),
            Mockito.mock(ScheduleData::class.java)
        )
        Mockito.`when`(remoteDataStore.getAll(Mockito.anyString())).thenReturn(remoteRes)

        val schedules = scheduleRepository.getAll(Mockito.anyString())
        assertEquals(transformToEntity(remoteRes), schedules)
        assertEquals(2, schedules.size)
    }

    @Test
    fun save_shouldReturnTrueWhenSuccess() {
        val schedule = Mockito.mock(ScheduleEntity::class.java)

        assertEquals(true, scheduleRepository.save(schedule))
    }

    @Test(expected = SQLException::class)
    fun save_shouldThrowExceptionWhenError() {
        val schedule = Mockito.mock(ScheduleEntity::class.java)

        Mockito.`when`(memoryDataStore.save(scheduleEntityToDataMapper.map(schedule)))
            .thenThrow(SQLException::class.java)

        scheduleRepository.save(schedule)
    }

    private fun transformToEntity(leagueData: MutableList<ScheduleData>) = leagueData.map {
        scheduleDataToEntityMapper.map(it)
    }.toMutableList()
}