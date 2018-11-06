package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository

class RemoveFavorite(private val favoriteRepository: FavoriteRepository) : UseCase<Schedule, Boolean>() {
    override suspend fun execute(data: Schedule): Boolean? {
        favoriteRepository.remove(data)
        return true
    }
}