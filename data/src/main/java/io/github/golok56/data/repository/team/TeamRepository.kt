package io.github.golok56.data.repository.team

import android.database.SQLException
import io.github.golok56.data.mapper.TeamDataToEntityMapper
import io.github.golok56.data.mapper.TeamEntityToDataMapper
import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.Repository

class TeamRepository(
    private val memoryDataStore: TeamMemoryDataStore,
    private val remoteDataStore: TeamRemoteDataStore,
    private val teamDataToEntityMapper: TeamDataToEntityMapper,
    private val teamEntityToDataMapper: TeamEntityToDataMapper
) : Repository<TeamEntity> {
    override suspend fun getAll(data: String): MutableList<TeamEntity> {
        val teams = memoryDataStore.getAll(data)
        return if (teams.isEmpty()) remoteDataStore.getAll(data)
            .map { teamDataToEntityMapper.map(it!!) }
            .toMutableList()
        else teams.map { teamDataToEntityMapper.map(it) }.toMutableList()
    }

    override suspend fun get(data: String): TeamEntity? {
        var team = memoryDataStore.get(data)
        if (team == null) {
            team = remoteDataStore.get(data)
        }

        team?.let { return@get teamDataToEntityMapper.map(it) }
        throw Exception("Tim tidak ditemukan")
    }

    override suspend fun save(item: TeamEntity): Boolean {
        try {
            memoryDataStore.save(teamEntityToDataMapper.map(item))
            return true
        } catch (ex: SQLException) {
            throw ex
        }
    }

    override suspend fun remove(item: TeamEntity): Boolean {
        memoryDataStore.remove(teamEntityToDataMapper.map(item))
        return true
    }
}