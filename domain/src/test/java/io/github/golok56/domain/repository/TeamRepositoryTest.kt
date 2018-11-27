package io.github.golok56.domain.repository

import io.github.golok56.domain.entities.TeamEntity

class TeamRepositoryTest : Repository<TeamEntity> {
    var teams = mutableListOf<TeamEntity>()

    override suspend fun getAll(data: String): MutableList<TeamEntity> {
        if (data == INVALID) throw Exception(ERROR_MESSAGE)
        return teams
    }

    override suspend fun get(data: String): TeamEntity? {
        if (data == INVALID) throw Exception(ERROR_MESSAGE)
        return teams.find { it.id == data }
    }

    override suspend fun save(item: TeamEntity): Boolean {
        if (item.id == INVALID) throw Exception(ERROR_MESSAGE)
        teams.add(item)
        return true
    }

    override suspend fun remove(item: TeamEntity): Boolean {
        if (item.id == INVALID) throw Exception(ERROR_MESSAGE)
        teams = teams.filter { it.id != item.id }.toMutableList()
        return true
    }

    companion object {
        const val INVALID = "-1"
        const val ERROR_MESSAGE = "Error id tidak boleh kurang dari 0"
    }
}