package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.repository.FavoriteRepository

class CheckFavorite(private val favoriteRepository: FavoriteRepository) : UseCase<String, Boolean>() {
    override suspend fun execute(data: String): Boolean? = favoriteRepository.get(data) != null
}