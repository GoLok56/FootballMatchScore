package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.PlayerEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SaveAllPlayers(
    private val playerRepository: Repository<PlayerEntity>,
    dispatchers: CoroutineContext
) : UseCase<MutableList<PlayerEntity>, Boolean>(dispatchers) {
    override suspend fun createOutput(data: MutableList<PlayerEntity>) =
        GlobalScope.async(dispatchers) {
            data.forEach { playerRepository.save(it) }
            true
        }
}