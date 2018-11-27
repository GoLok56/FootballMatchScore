package io.github.golok56.footballmatchscore.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.domain.usecase.FindAllFavoriteTeams
import io.github.golok56.domain.usecase.FindAllTeams
import io.github.golok56.domain.usecase.SaveAllTeams

class TeamViewModelFactory(
    private val findAllTeams: FindAllTeams,
    private val findAllFavoriteTeams: FindAllFavoriteTeams,
    private val saveAllTeams: SaveAllTeams,
    private val leagueId: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java))
            return TeamViewModel(findAllTeams, findAllFavoriteTeams, saveAllTeams, leagueId) as T
        throw IllegalArgumentException()
    }
}