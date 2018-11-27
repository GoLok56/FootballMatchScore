package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class AddFavorite(
    private val favoriteRepository: Repository<ScheduleEntity>,
    dispatcher: CoroutineContext
) : UseCase<ScheduleEntity, Boolean>(dispatcher) {
    override suspend fun createOutput(data: ScheduleEntity) = GlobalScope.async(dispatchers) {
        favoriteRepository.save(data)
    }
}