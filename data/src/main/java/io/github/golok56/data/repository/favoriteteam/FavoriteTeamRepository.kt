package io.github.golok56.data.repository.favoriteteam

import io.github.golok56.data.mapper.TeamDataToEntityMapper
import io.github.golok56.data.mapper.TeamEntityToDataMapper
import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.Repository
import java.sql.SQLException

class FavoriteTeamRepository(
    private val favoriteTeamMemoryDataStore: FavoriteTeamMemoryDataStore,
    private val teamDataToEntityMapper: TeamDataToEntityMapper,
    private val teamEntityToDataMapper: TeamEntityToDataMapper
) : Repository<TeamEntity> {
    override suspend fun getAll(data: String): MutableList<TeamEntity> {
        val teams = favoriteTeamMemoryDataStore.getAll(data)
        if (teams.isEmpty()) throw Exception("Belum ditemukan tim favorit")
        return teams.map { teamDataToEntityMapper.map(it) }.toMutableList()
    }

    override suspend fun get(data: String): TeamEntity? {
        favoriteTeamMemoryDataStore.get(data)?.let { return@get teamDataToEntityMapper.map(it) }
        return null
    }

    override suspend fun save(item: TeamEntity): Boolean {
        try {
            favoriteTeamMemoryDataStore.save(teamEntityToDataMapper.map(item))
            return true
        } catch (ex: SQLException) {
            throw ex
        }
    }

    override suspend fun remove(item: TeamEntity): Boolean {
        favoriteTeamMemoryDataStore.remove(teamEntityToDataMapper.map(item))
        return true
    }
}