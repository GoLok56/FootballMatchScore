package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SaveAllLeagues(
    private val leagueRepository: Repository<LeagueEntity>,
    dispatchers: CoroutineContext
) : UseCase<MutableList<LeagueEntity>, Boolean>(dispatchers) {
    override suspend fun createOutput(data: MutableList<LeagueEntity>) =
        GlobalScope.async(dispatchers) {
            data.forEach { leagueRepository.save(it) }
            true
        }
}