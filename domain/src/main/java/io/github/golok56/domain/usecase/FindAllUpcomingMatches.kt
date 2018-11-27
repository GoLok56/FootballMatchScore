package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class FindAllUpcomingMatches(
    private val upcomingMatchRepository: Repository<ScheduleEntity>,
    dispatchers: CoroutineContext
) : UseCase<String, MutableList<ScheduleEntity>>(dispatchers) {
    override suspend fun createOutput(data: String) = GlobalScope.async(dispatchers) {
        upcomingMatchRepository.getAll(data)
    }
}