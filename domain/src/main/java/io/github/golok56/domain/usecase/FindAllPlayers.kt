package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.PlayerEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class FindAllPlayers(
    private val playerRepository: Repository<PlayerEntity>,
    dispatchers: CoroutineContext
) : UseCase<String, MutableList<PlayerEntity>>(dispatchers) {
    override suspend fun createOutput(data: String) = GlobalScope.async(dispatchers) {
        playerRepository.getAll(data)
    }
}