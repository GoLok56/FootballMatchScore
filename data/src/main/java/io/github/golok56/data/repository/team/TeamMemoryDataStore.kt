package io.github.golok56.data.repository.team

import android.database.SQLException
import io.github.golok56.data.entities.TeamData
import io.github.golok56.domain.repository.DataStore
import java.util.*

class TeamMemoryDataStore(private val teamCache: TeamCache) : DataStore<TeamData> {
    override suspend fun getAll(data: String): MutableList<TeamData> {
        if (teamCache.isEmpty()) return mutableListOf()

        val teams = teamCache.getAll().filter { it.leagueId == data }.toMutableList()
        if (teams.isEmpty()) return mutableListOf()

        var isExpired = false
        teams.forEach { if (teamCache.isExpired(it)) isExpired = true }
        if (isExpired) {
            teamCache.clear()
            return mutableListOf()
        }

        return teams
    }

    override suspend fun get(data: String): TeamData? {
        val team = teamCache.get(data)
        team?.let {
            if (teamCache.isExpired(it)) {
                teamCache.remove(it)
                return@get null
            }

            return@get it
        }
        return null
    }

    override suspend fun save(item: TeamData) {
        item.updatedOn = Date().time
        if (!teamCache.put(item)) throw SQLException("Terjadi kesalahan saat melakukan save ke SQLite")
    }

    override suspend fun remove(item: TeamData) = teamCache.remove(item)
}
