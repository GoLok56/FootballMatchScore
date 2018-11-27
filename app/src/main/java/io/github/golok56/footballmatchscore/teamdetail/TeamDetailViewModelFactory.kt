package io.github.golok56.footballmatchscore.teamdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.domain.usecase.AddFavoriteTeam
import io.github.golok56.domain.usecase.CheckFavoriteTeam
import io.github.golok56.domain.usecase.FindAllPlayers
import io.github.golok56.domain.usecase.RemoveFavoriteTeam

class TeamDetailViewModelFactory(
    private val findAllPlayers: FindAllPlayers,
    private val checkFavoriteTeam: CheckFavoriteTeam,
    private val addFavoriteTeam: AddFavoriteTeam,
    private val removeFavoriteTeam: RemoveFavoriteTeam
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamDetailViewModel::class.java))
            return TeamDetailViewModel(
                findAllPlayers,
                checkFavoriteTeam,
                addFavoriteTeam,
                removeFavoriteTeam
            ) as T
        throw IllegalArgumentException()
    }
}