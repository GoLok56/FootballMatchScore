package io.github.golok56.footballmatchscore.teamdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.domain.usecase.AddFavoriteTeam
import io.github.golok56.domain.usecase.CheckFavoriteTeam
import io.github.golok56.domain.usecase.FindAllPlayers
import io.github.golok56.domain.usecase.RemoveFavoriteTeam
import io.github.golok56.footballmatchscore.mapper.PlayerEntityToModelMapper
import io.github.golok56.footballmatchscore.mapper.TeamModelToEntityMapper
import io.github.golok56.footballmatchscore.model.Team

class TeamDetailViewModel(
    private val findAllPlayers: FindAllPlayers,
    private val checkFavoriteTeam: CheckFavoriteTeam,
    private val addFavoriteTeam: AddFavoriteTeam,
    private val removeFavoriteTeam: RemoveFavoriteTeam
) : ViewModel() {
    private var teamModelToEntityMapper = TeamModelToEntityMapper()
    private var playerEntityToModelMapper = PlayerEntityToModelMapper()

    val viewState = MutableLiveData<TeamDetailViewState>().apply {
        value = TeamDetailViewState(false, mutableMapOf())
    }

    fun findPlayers(teamId: String) {
        findAllPlayers.execute(teamId) { result, err ->
            viewState.value = viewState.value?.apply {
                data[PLAYER] = result?.map { playerEntityToModelMapper.map(it) }?.toMutableList()
                error = err?.message
            }
        }
    }

    fun isFavorite(teamId: String) {
        checkFavoriteTeam.execute(teamId) { result, err ->
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = result
                error = err?.message
            }
        }
    }

    fun removeFavorite(team: Team) {
        removeFavoriteTeam.execute(teamModelToEntityMapper.map(team)) { _, err ->
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = false
                error = err?.message
            }
        }
    }

    fun addFavorite(team: Team) {
        addFavoriteTeam.execute(teamModelToEntityMapper.map(team)) { _, err ->
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = true
                error = err?.message
            }
        }
    }

    companion object {
        const val FAVORITE = "favorite"
        const val PLAYER = "player"
    }
}