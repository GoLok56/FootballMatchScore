package io.github.golok56.data.repository.league

import android.database.SQLException
import io.github.golok56.data.entities.LeagueData
import io.github.golok56.domain.repository.DataStore
import java.util.*

class LeagueMemoryDataStore(private val leagueCache: LeagueCache) : DataStore<LeagueData> {
    override suspend fun getAll(data: String): MutableList<LeagueData> {
        if (leagueCache.isEmpty()) {
            return mutableListOf()
        }

        val leagues = leagueCache.getAll()
        var isExpired = false
        leagues.forEach { if (leagueCache.isExpired(it)) isExpired = true }
        if (isExpired) {
            leagueCache.clear()
            return mutableListOf()
        }

        return leagues
    }

    override suspend fun get(data: String): LeagueData? {
        val league = leagueCache.get(data)
        league?.let {
            if (leagueCache.isExpired(it)) {
                leagueCache.remove(it)
                return null
            }

            return it
        }

        return null
    }

    override suspend fun save(item: LeagueData) {
        item.updatedOn = Date().time
        if (!leagueCache.put(item)) throw SQLException("Terjadi kesalahan saat melakukan save ke SQLite")
    }

    override suspend fun remove(item: LeagueData) = leagueCache.remove(item)
}