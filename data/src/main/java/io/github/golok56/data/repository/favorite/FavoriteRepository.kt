package io.github.golok56.data.repository.favorite

import io.github.golok56.data.mapper.ScheduleDataToEntityMapper
import io.github.golok56.data.mapper.ScheduleEntityToDataMapper
import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.Repository
import java.sql.SQLException

class FavoriteRepository(
    private val favoriteMemoryDataStore: FavoriteMemoryDataStore,
    private val scheduleDataToEntityMapper: ScheduleDataToEntityMapper,
    private val scheduleEntityToDataMapper: ScheduleEntityToDataMapper
) : Repository<ScheduleEntity> {
    override suspend fun getAll(data: String): MutableList<ScheduleEntity> {
        val schedules = favoriteMemoryDataStore.getAll(data)
        if (schedules.isEmpty()) throw Exception("Belum ditemukan pertandingan favorit")
        return schedules.map { scheduleDataToEntityMapper.map(it) }.toMutableList()
    }

    override suspend fun get(data: String): ScheduleEntity? {
        favoriteMemoryDataStore.get(data)?.let { return@get scheduleDataToEntityMapper.map(it) }
        return null
    }

    override suspend fun save(item: ScheduleEntity): Boolean {
        try {
            favoriteMemoryDataStore.save(scheduleEntityToDataMapper.map(item))
            return true
        } catch (ex: SQLException) {
            throw ex
        }
    }

    override suspend fun remove(item: ScheduleEntity): Boolean {
        favoriteMemoryDataStore.remove(scheduleEntityToDataMapper.map(item))
        return true
    }
}