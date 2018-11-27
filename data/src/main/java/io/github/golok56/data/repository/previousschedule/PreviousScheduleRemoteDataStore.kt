package io.github.golok56.data.repository.previousschedule

import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.data.services.ScheduleService
import io.github.golok56.domain.repository.DataStore

class PreviousScheduleRemoteDataStore(
    private val scheduleApi: ScheduleService
) : DataStore<ScheduleData> {
    override suspend fun getAll(data: String): MutableList<ScheduleData> {
        val response = scheduleApi.findPreviousMatches(data).await()
        if (response.isSuccessful) {
            return response.body()?.events?: mutableListOf()
        }

        throw Exception("Terjadi kesalahan saat melakukan request ke thesportsdb")
    }

    override suspend fun get(data: String): Nothing? = null

    override suspend fun save(item: ScheduleData) {
    }

    override suspend fun remove(item: ScheduleData) {
    }
}
