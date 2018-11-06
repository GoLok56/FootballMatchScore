package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository
import java.util.*

class SaveAllLeagues(private val leagueRepository: LeagueRepository) : UseCase<MutableList<League>, Boolean>() {
    override suspend fun execute(data: MutableList<League>): Boolean? {
        data.forEach{
            it.updatedOn = Date().time
            leagueRepository.save(it)
        }
        return true
    }
}