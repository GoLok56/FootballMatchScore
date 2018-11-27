package io.github.golok56.data.repository.favoriteteam

import io.github.golok56.data.entities.TeamData
import io.github.golok56.domain.repository.DataStore
import java.sql.SQLException

class FavoriteTeamMemoryDataStore(
    private val favoriteTeamCache: FavoriteTeamCache
) : DataStore<TeamData> {
    override suspend fun getAll(data: String) = favoriteTeamCache.getAll()

    override suspend fun get(data: String) = favoriteTeamCache.get(data)

    override suspend fun save(item: TeamData) {
        if (!favoriteTeamCache.put(item)) throw SQLException("Terjadi kesalahan saat menyimpan data")
    }

    override suspend fun remove(item: TeamData) = favoriteTeamCache.remove(item)
}