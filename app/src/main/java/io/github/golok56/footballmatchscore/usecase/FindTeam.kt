package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.repository.TeamRepository

class FindTeam(private val teamRepository: TeamRepository) : UseCase<String, Team>() {
    override suspend fun execute(data: String): Team? {
        val response = teamRepository.findTeams(data).await()

        if (response.isSuccessful) {
            val scheduleResponse = response.body()?.teams?.get(0)
            teamRepository.teams[data] = scheduleResponse
            return scheduleResponse
        }

        throw Exception("Terjadi kesalahan saat fetch data dari thesportsdb!")
    }
}