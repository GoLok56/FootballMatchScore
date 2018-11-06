package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.PreviousScheduleRepository

class FindAllLastMatches(
    private val scheduleRepository: PreviousScheduleRepository
) : UseCase<String, MutableList<Schedule>>() {
    override suspend fun execute(data: String): MutableList<Schedule>? {
        val response = scheduleRepository.findAllLastMatches(data).await()

        if (response.isSuccessful) {
            val scheduleResponse = response.body()?.events
            scheduleResponse?.let { scheduleRepository.save(it) }
            return scheduleResponse
        }

        throw Exception("Terjadi kesalahan saat fetch data dari thesportsdb!")
    }
}