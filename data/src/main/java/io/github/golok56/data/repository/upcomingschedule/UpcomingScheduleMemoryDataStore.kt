package io.github.golok56.data.repository.upcomingschedule

import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.domain.repository.DataStore

class UpcomingScheduleMemoryDataStore(
    private val upcomingScheduleCache: UpcomingScheduleCache
) : DataStore<ScheduleData> {
    override suspend fun getAll(data: String) = upcomingScheduleCache.getAll(data)?: mutableListOf()

    override suspend fun get(data: String): Nothing? = null

    override suspend fun save(item: ScheduleData) {
        if(!upcomingScheduleCache.put(item)) throw Exception("Terjadi kesalahan saat menyimpan data")
    }

    override suspend fun remove(item: ScheduleData) = upcomingScheduleCache.remove(item)
}
