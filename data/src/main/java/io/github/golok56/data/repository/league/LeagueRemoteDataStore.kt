package io.github.golok56.data.repository.league

import io.github.golok56.data.entities.LeagueData
import io.github.golok56.data.services.LeagueService
import io.github.golok56.domain.repository.DataStore
import java.lang.Exception

class LeagueRemoteDataStore(private val leagueApi: LeagueService) : DataStore<LeagueData> {
    override suspend fun getAll(data: String): MutableList<LeagueData> {
        val response = leagueApi.findAllLeagues().await()
        if (response.isSuccessful) {
            return response.body()?.leagues ?: mutableListOf()
        }

        throw Exception("Terjadi kesalahan saat melakukan request ke thesportsdb")
    }

    override suspend fun get(data: String): LeagueData? {
        val response = leagueApi.findLeague(data).await()
        if (response.isSuccessful) {
            return response.body()?.leagues?.get(0)
        }

        throw Exception("Terjadi kesalahan saat melakukan request ke thesportsdb")
    }

    override suspend fun save(item: LeagueData) {
    }

    override suspend fun remove(item: LeagueData) {
    }
}