package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class FindAllSoccerLeague(
    private val leagueRepository: Repository<LeagueEntity>,
    dispatchers: CoroutineContext
) : UseCase<String, MutableList<LeagueEntity>>(dispatchers) {
    override suspend fun createOutput(data: String) = GlobalScope.async(dispatchers) {
        leagueRepository.getAll(data).filter { it.sport == "Soccer" }.toMutableList()
    }
}