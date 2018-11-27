package io.github.golok56.data.repository.league

import android.database.SQLException
import io.github.golok56.data.mapper.LeagueDataToEntityMapper
import io.github.golok56.data.mapper.LeagueEntityToDataMapper
import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.Repository

class LeagueRepository(
    private val memoryDataStore: LeagueMemoryDataStore,
    private val remoteDataStore: LeagueRemoteDataStore,
    private val leagueDataToEntityMapper: LeagueDataToEntityMapper,
    private val leagueEntityToDataMapper: LeagueEntityToDataMapper
) : Repository<LeagueEntity> {
    override suspend fun getAll(data: String): MutableList<LeagueEntity> {
        val leagues = memoryDataStore.getAll("")
        return if (leagues.isEmpty()) remoteDataStore.getAll("")
            .map { leagueDataToEntityMapper.map(it) }
            .toMutableList()
        else leagues.map { leagueDataToEntityMapper.map(it) }.toMutableList()
    }

    override suspend fun get(data: String): LeagueEntity {
        var league = memoryDataStore.get(data)
        if (league == null) {
            league = remoteDataStore.get(data)
        }

        league?.let { return@get leagueDataToEntityMapper.map(it) }
        throw Exception("Liga tidak ditemukan")
    }

    override suspend fun save(item: LeagueEntity): Boolean {
        try {
            memoryDataStore.save(leagueEntityToDataMapper.map(item))
            return true
        } catch (ex: SQLException) {
            throw ex
        }
    }

    override suspend fun remove(item: LeagueEntity): Boolean {
        memoryDataStore.remove(leagueEntityToDataMapper.map(item))
        return true
    }
}