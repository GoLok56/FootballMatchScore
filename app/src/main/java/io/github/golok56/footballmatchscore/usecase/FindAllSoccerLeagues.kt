package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository

class FindAllSoccerLeagues(private val leagueRespository: LeagueRepository) : UseCase<Boolean, MutableList<League>>() {
    /**
     * @param data indicating should the data be refreshed or not.
     */
    override suspend fun execute(data: Boolean): MutableList<League>? {
        if (data) {
            leagueRespository.leagues = null
        }

        val response = leagueRespository.findAllLeagues().await()
        if (response.isSuccessful) {
            val leagueResponse = response.body()?.leagues?.filter { it.sport == "Soccer" }?.toMutableList()
            leagueRespository.leagues = leagueResponse
            return leagueResponse
        }

        throw Exception("Terjadi kesalahan saat mengambil data liga dari thesportsdb!")
    }
}