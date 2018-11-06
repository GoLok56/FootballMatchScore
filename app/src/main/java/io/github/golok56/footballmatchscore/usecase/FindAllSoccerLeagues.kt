package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository

class FindAllSoccerLeagues(private val leagueRespository: LeagueRepository) : UseCase<Boolean, MutableList<League>>() {
    override suspend fun execute(data: Boolean): MutableList<League>? {
        val response = leagueRespository.findAllLeagues().await()
        if (response.isSuccessful) {
            return response.body()?.leagues?.filter { it.sport == "Soccer" }?.toMutableList()
        }

        throw Exception("Terjadi kesalahan saat mengambil data liga dari thesportsdb!")
    }
}