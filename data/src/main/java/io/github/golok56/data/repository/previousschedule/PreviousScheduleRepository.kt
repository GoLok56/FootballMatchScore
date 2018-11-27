package io.github.golok56.data.repository.previousschedule

import android.database.SQLException
import io.github.golok56.data.mapper.ScheduleDataToEntityMapper
import io.github.golok56.data.mapper.ScheduleEntityToDataMapper
import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.Repository

class PreviousScheduleRepository(
    private val memoryDataStore: PreviousScheduleMemoryDataStore,
    private val remoteDataStore: PreviousScheduleRemoteDataStore,
    private val scheduleDataToEntityMapper: ScheduleDataToEntityMapper,
    private val scheduleEntityToDataMapper: ScheduleEntityToDataMapper
) : Repository<ScheduleEntity> {
    override suspend fun getAll(data: String): MutableList<ScheduleEntity> {
        val schedules = memoryDataStore.getAll(data)
        return if (schedules.isEmpty()) remoteDataStore.getAll(data)
            .map { scheduleDataToEntityMapper.map(it) }
            .toMutableList()
        else schedules.map { scheduleDataToEntityMapper.map(it) }.toMutableList()
    }

    override suspend fun get(data: String): Nothing? = null

    override suspend fun save(item: ScheduleEntity): Boolean {
        try {
            memoryDataStore.save(scheduleEntityToDataMapper.map(item))
            return true
        } catch (ex: SQLException) {
            throw ex
        }
    }

    override suspend fun remove(item: ScheduleEntity): Boolean {
        memoryDataStore.remove(scheduleEntityToDataMapper.map(item))
        return true
    }
}