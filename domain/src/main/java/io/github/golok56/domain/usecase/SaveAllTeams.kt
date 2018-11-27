package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SaveAllTeams(
    private val teamRepository: Repository<TeamEntity>,
    dispatchers: CoroutineContext
) : UseCase<MutableList<TeamEntity>, Boolean>(dispatchers) {
    override suspend fun createOutput(data: MutableList<TeamEntity>) =
        GlobalScope.async(dispatchers) {
            data.forEach { teamRepository.save(it) }
            true
        }
}