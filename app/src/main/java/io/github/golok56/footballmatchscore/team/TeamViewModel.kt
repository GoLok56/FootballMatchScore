package io.github.golok56.footballmatchscore.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.usecase.FindAllFavoriteTeams
import io.github.golok56.domain.usecase.FindAllTeams
import io.github.golok56.domain.usecase.SaveAllTeams
import io.github.golok56.footballmatchscore.mapper.TeamEntityToModelMapper
import io.github.golok56.footballmatchscore.model.Team

class TeamViewModel(
    private val findAllTeams: FindAllTeams,
    private val findAllFavoriteTeams: FindAllFavoriteTeams,
    private val saveAllTeams: SaveAllTeams,
    private val leagueId: String
) : ViewModel() {
    private var teams = mutableMapOf<String, MutableList<Team>?>()
    private var teamEntityToModelMapper = TeamEntityToModelMapper()

    val viewState = MutableLiveData<TeamViewState>()

    init {
        viewState.value = TeamViewState(true)
    }

    fun fetchTeams() {
        viewState.value = viewState.value?.copy(loading = true)
        findAllTeams.execute(leagueId) { result, err ->
            updateViewState(result, err, TEAM)
            result?.let { if (it.size != 0 && it[0].updatedOn == null) saveAllTeams.execute(it) }
        }
    }

    fun fetchFavsTeam() {
        viewState.value = viewState.value?.copy(loading = true)
        findAllFavoriteTeams.execute("") { result, err -> updateViewState(result, err, FAVS) }
    }

    fun filter(text: String) {
        viewState.value = viewState.value?.apply {
            data[FAVS] = teams[FAVS]?.filter {
                it.name?.toLowerCase()?.contains(text.toLowerCase())!!
            }?.toMutableList()
            data[TEAM] = teams[TEAM]?.filter {
                it.name?.toLowerCase()?.contains(text.toLowerCase())!!
            }?.toMutableList()
        }
    }

    private fun updateViewState(
        result: MutableList<TeamEntity>?,
        err: Exception?,
        which: String
    ) {
        val list = result?.map { teamEntityToModelMapper.map(it) }?.toMutableList()
        teams[which] = list
        viewState.value = viewState.value?.apply {
            loading = false
            data[which] = list
            error = err?.message
        }
    }

    companion object {
        const val TEAM = "team"
        const val FAVS = "favs"
    }
}