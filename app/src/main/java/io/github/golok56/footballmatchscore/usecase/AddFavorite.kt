package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository

class AddFavorite(private val favoriteRepository: FavoriteRepository) : UseCase<Schedule, Boolean>() {
    override suspend fun execute(data: Schedule): Boolean? {
        favoriteRepository.save(data)
        return true
    }
}