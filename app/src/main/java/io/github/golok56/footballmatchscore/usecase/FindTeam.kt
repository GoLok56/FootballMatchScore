package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.repository.TeamRepository
import java.util.*

class FindTeam(private val teamRepository: TeamRepository) : UseCase<String, Team>() {
    override suspend fun execute(data: String): Team? {
        val response = teamRepository.findTeam(data).await()

        if (response.isSuccessful) {
            val teamResponse = response.body()?.teams?.get(0)
            teamResponse?.let {
                it.updatedOn = Date().time
                teamRepository.save(it)
            }
            return teamResponse
        }

        throw Exception("Terjadi kesalahan saat fetch data dari thesportsdb!")
    }
}