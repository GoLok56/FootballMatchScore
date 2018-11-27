package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class CheckFavorite(
    private val scheduleRepository: Repository<ScheduleEntity>,
    dispatchers: CoroutineContext
) : UseCase<String, Boolean>(dispatchers) {
    override suspend fun createOutput(data: String) = GlobalScope.async(dispatchers) {
        scheduleRepository.get(data) != null
    }
}