package io.github.golok56.data.repository.player

import io.github.golok56.data.mapper.PlayerDataToEntityMapper
import io.github.golok56.data.mapper.PlayerEntityToDataMapper
import io.github.golok56.domain.entities.PlayerEntity
import io.github.golok56.domain.repository.Repository
import java.sql.SQLException

class PlayerRepository(
    private val memoryDataStore: PlayerMemoryDataStore,
    private val remoteDataStore: PlayerRemoteDataStore,
    private val playerDataToEntityMapper: PlayerDataToEntityMapper,
    private val playerEntityToDataMapper: PlayerEntityToDataMapper
) : Repository<PlayerEntity> {
    override suspend fun getAll(data: String): MutableList<PlayerEntity> {
        val players = memoryDataStore.getAll(data)
        return if (players.isEmpty()) remoteDataStore.getAll(data)
            .map { playerDataToEntityMapper.map(it) }
            .toMutableList()
        else players.map { playerDataToEntityMapper.map(it) }.toMutableList()
    }

    override suspend fun get(data: String) = null

    override suspend fun save(item: PlayerEntity): Boolean {
        try {
            memoryDataStore.save(playerEntityToDataMapper.map(item))
            return true
        } catch (ex: SQLException) {
            throw ex
        }
    }

    override suspend fun remove(item: PlayerEntity): Boolean {
        memoryDataStore.remove(playerEntityToDataMapper.map(item))
        return true
    }
}