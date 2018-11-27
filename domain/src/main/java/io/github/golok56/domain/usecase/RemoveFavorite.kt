package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class RemoveFavorite(
    private val favoriteRepository: Repository<ScheduleEntity>,
    dispatchers: CoroutineContext
) : UseCase<ScheduleEntity, Boolean>(dispatchers) {
    override suspend fun createOutput(data: ScheduleEntity) = GlobalScope.async(dispatchers) {
        favoriteRepository.remove(data)
    }
}