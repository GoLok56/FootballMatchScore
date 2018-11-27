package io.github.golok56.domain.repository

import io.github.golok56.domain.entities.LeagueEntity

class LeagueRepositoryTest : Repository<LeagueEntity> {
    var leagues = mutableListOf<LeagueEntity>()

    override suspend fun getAll(data: String): MutableList<LeagueEntity> {
        if (data == INVALID) throw Exception(ERROR_MESSAGE)
        return leagues
    }

    override suspend fun get(data: String): LeagueEntity? {
        if (data == INVALID) throw Exception(ERROR_MESSAGE)
        return leagues.find { it.id == data }
    }

    override suspend fun save(item: LeagueEntity): Boolean {
        if (item.id == INVALID) throw Exception(ERROR_MESSAGE)
        return leagues.add(item)
    }

    override suspend fun remove(item: LeagueEntity): Boolean {
        if (item.id == INVALID) throw Exception(ERROR_MESSAGE)
        return leagues.remove(item)
    }

    companion object {
        const val INVALID = "-1"
        const val ERROR_MESSAGE = "Error id tidak boleh kurang dari 0"
    }
}