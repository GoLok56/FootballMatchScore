package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository

class FindLeagueDetail(private val leagueRepository: LeagueRepository) : UseCase<String?, League>() {
    override suspend fun execute(data: String?): League? {
        val response = leagueRepository.findLeague(data).await()

        if (response.isSuccessful) {
            return response.body()?.leagues?.get(0)
        }

        throw Exception("Terjadi kesalahan saat fetch data dari thesportsdb!")
    }
}