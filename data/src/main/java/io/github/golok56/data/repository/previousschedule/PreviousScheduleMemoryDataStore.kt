package io.github.golok56.data.repository.previousschedule

import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.domain.repository.DataStore

class PreviousScheduleMemoryDataStore(
    private val previousScheduleCache: PreviousScheduleCache
) : DataStore<ScheduleData> {
    override suspend fun getAll(data: String) = previousScheduleCache.getAll(data)?: mutableListOf()

    override suspend fun get(data: String): Nothing? = null

    override suspend fun save(item: ScheduleData) {
        if(!previousScheduleCache.put(item)) throw Exception("Terjadi kesalahan saat menyimpan data")
    }

    override suspend fun remove(item: ScheduleData) = previousScheduleCache.remove(item)
}
