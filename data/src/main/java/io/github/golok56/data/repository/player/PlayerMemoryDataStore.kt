package io.github.golok56.data.repository.player

import android.database.SQLException
import io.github.golok56.data.entities.PlayerData
import io.github.golok56.domain.repository.DataStore
import java.util.*

class PlayerMemoryDataStore(private val playerCache: PlayerCache) : DataStore<PlayerData> {
    override suspend fun getAll(data: String): MutableList<PlayerData> {
        if (playerCache.isEmpty()) return mutableListOf()

        val players = playerCache.getAll().filter { it.teamId == data }.toMutableList()
        if (players.isEmpty()) return mutableListOf()

        var isExpired = false
        players.forEach { if (playerCache.isExpired(it)) isExpired = true }
        if (isExpired) {
            playerCache.clear()
            return mutableListOf()
        }

        return players
    }

    override suspend fun get(data: String): PlayerData? {
        val player = playerCache.get(data)
        player?.let {
            if (playerCache.isExpired(it)) {
                playerCache.remove(it)
                return@get null
            }

            return@get it
        }
        return null
    }

    override suspend fun save(item: PlayerData) {
        item.updatedOn = Date().time
        if (!playerCache.put(item)) throw SQLException("Terjadi kesalahan saat melakukan save ke SQLite")
    }

    override suspend fun remove(item: PlayerData) = playerCache.remove(item)
}