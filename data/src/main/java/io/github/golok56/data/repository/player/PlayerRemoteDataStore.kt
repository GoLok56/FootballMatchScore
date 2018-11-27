package io.github.golok56.data.repository.player

import io.github.golok56.data.entities.PlayerData
import io.github.golok56.data.services.PlayerService
import io.github.golok56.domain.repository.DataStore

class PlayerRemoteDataStore(private val playerApi: PlayerService) : DataStore<PlayerData> {
    override suspend fun getAll(data: String): MutableList<PlayerData> {
        val response = playerApi.findAllPlayer(data).await()
        if (response.isSuccessful) {
            return response.body()?.player ?: mutableListOf()
        }

        throw Exception("Terjadi kesalahan saat melakukan request ke thesportsdb")
    }

    override suspend fun get(data: String) = null

    override suspend fun save(item: PlayerData) {
    }

    override suspend fun remove(item: PlayerData) {
    }
}