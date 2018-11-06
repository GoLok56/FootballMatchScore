package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.NextScheduleRepository

class FindAllNextMatches(
    private val scheduleRepository: NextScheduleRepository
) : UseCase<String, MutableList<Schedule>>() {
    override suspend fun execute(data: String): MutableList<Schedule>? {
        val response = scheduleRepository.findAllNextMatches(data).await()

        if (response.isSuccessful) {
            val scheduleResponse = response.body()?.events
            scheduleResponse?.let { scheduleRepository.save(it) }
            return scheduleResponse
        }

        throw Exception("Terjadi kesalahan saat fetch data dari thesportsdb!")
    }
}