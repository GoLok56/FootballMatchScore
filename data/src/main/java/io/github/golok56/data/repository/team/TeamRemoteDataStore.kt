package io.github.golok56.data.repository.team

import io.github.golok56.data.entities.TeamData
import io.github.golok56.data.services.TeamService
import io.github.golok56.domain.repository.DataStore

class TeamRemoteDataStore(private val teamApi: TeamService) : DataStore<TeamData?> {
    override suspend fun getAll(data: String): MutableList<TeamData?> {
        val response = teamApi.findAllTeams(data).await()
        if (response.isSuccessful) {
            return response.body()?.teams ?: mutableListOf()
        }

        throw Exception("Terjadi kesalahan saat melakukan request ke thesportsdb")
    }

    override suspend fun get(data: String): TeamData? {
        val response = teamApi.findTeam(data).await()
        if (response.isSuccessful) {
            return response.body()?.teams?.get(0)
        }

        throw Exception("Terjadi kesalahan saat melakukan request ke thesportsdb")
    }

    override suspend fun save(item: TeamData?) {
    }

    override suspend fun remove(item: TeamData?) {
    }
}