package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class FindAllLeaguesDetail(
    private val leagueRepository: Repository<LeagueEntity>,
    dispatchers: CoroutineContext
) : UseCase<MutableList<LeagueEntity>, MutableList<LeagueEntity>>(dispatchers) {
    override suspend fun createOutput(data: MutableList<LeagueEntity>) =
        GlobalScope.async(dispatchers) {
            val leaguesDetail = mutableListOf<LeagueEntity>()
            data.forEach {
                leagueRepository.get(it.id!!)?.let { league -> leaguesDetail.add(league) }
            }

            leaguesDetail
        }
}