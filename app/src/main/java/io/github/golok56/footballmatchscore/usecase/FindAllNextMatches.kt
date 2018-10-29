package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.ScheduleRepository

class FindAllNextMatches(
    private val scheduleRepository: ScheduleRepository
) : UseCase<String, MutableList<Schedule>>() {
    override suspend fun execute(data: String): MutableList<Schedule>? {
        val response = scheduleRepository.findAllNextMatches(data).await()

        if (response.isSuccessful) {
            val scheduleResponse = response.body()?.events
            scheduleRepository.nextMatches[data] = scheduleResponse
            return scheduleResponse
        }

        throw Exception("Terjadi kesalahan saat fetch data dari thesportsdb!")
    }
}