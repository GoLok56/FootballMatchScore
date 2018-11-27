package io.github.golok56.data.repository.favorite

import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.domain.repository.DataStore
import java.sql.SQLException

class FavoriteMemoryDataStore(private val favoriteCache: FavoriteCache) : DataStore<ScheduleData> {
    override suspend fun getAll(data: String) = favoriteCache.getAll()

    override suspend fun get(data: String) = favoriteCache.get(data)

    override suspend fun save(item: ScheduleData) {
        if (!favoriteCache.put(item)) throw SQLException("Terjadi kesalahan saat menyimpan data")
    }

    override suspend fun remove(item: ScheduleData) = favoriteCache.remove(item)
}
