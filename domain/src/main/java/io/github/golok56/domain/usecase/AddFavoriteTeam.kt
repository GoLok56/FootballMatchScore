package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class AddFavoriteTeam(
    private val teamFavoriteRepository: Repository<TeamEntity>,
    dispatchers: CoroutineContext
) : UseCase<TeamEntity, Boolean>(dispatchers) {
    override suspend fun createOutput(data: TeamEntity) = GlobalScope.async(dispatchers) {
        teamFavoriteRepository.save(data)
    }
}