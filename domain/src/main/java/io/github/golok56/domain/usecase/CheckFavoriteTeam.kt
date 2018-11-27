package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class CheckFavoriteTeam(
    private val favoriteTeamRepository: Repository<TeamEntity>,
    dispatchers: CoroutineContext
) : UseCase<String, Boolean>(dispatchers) {
    override suspend fun createOutput(data: String) = GlobalScope.async {
        favoriteTeamRepository.get(data) != null
    }
}