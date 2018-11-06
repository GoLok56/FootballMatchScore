package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository

class FindFavoriteMatches(private val favoriteRepository: FavoriteRepository)
    : UseCase<Boolean, MutableList<Schedule>>() {
    override suspend fun execute(data: Boolean): MutableList<Schedule>? =
        favoriteRepository.getAll()
}