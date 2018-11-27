package io.github.golok56.footballmatchscore.scheduledetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.domain.usecase.*
import io.github.golok56.footballmatchscore.mapper.LeagueEntityToModelMapper
import io.github.golok56.footballmatchscore.mapper.ScheduleModelToEntityMapper
import io.github.golok56.footballmatchscore.mapper.TeamEntityToModelMapper
import io.github.golok56.footballmatchscore.model.Schedule

class ScheduleDetailViewModel(
    private val findTeam: FindTeam,
    private val findLeagueDetail: FindLeagueDetail,
    private val checkFavorite: CheckFavorite,
    private val removeFavorite: RemoveFavorite,
    private val addFavorite: AddFavorite
) : ViewModel() {
    private var scheduleModelToEntityMapper = ScheduleModelToEntityMapper()
    private var teamEntityToModelMapper = TeamEntityToModelMapper()
    private var leagueEntityToModelMapper = LeagueEntityToModelMapper()

    val viewState = MutableLiveData<ScheduleDetailViewState>()

    init {
        viewState.value = ScheduleDetailViewState(false, mutableMapOf())
    }

    fun find(teamId: String, side: String) {
        findTeam.execute(teamId) { result, err ->
            viewState.value = viewState.value?.apply {
                data[side] = result?.let { teamEntityToModelMapper.map(it) }
                error = err?.message
            }
        }
    }

    fun getLeague(leagueId: String) {
        findLeagueDetail.execute(leagueId) { result, err ->
            viewState.value = viewState.value?.apply {
                data[LEAGUE] = result?.let { leagueEntityToModelMapper.map(it) }
                error = err?.message
            }
        }
    }

    fun isFavorite(scheduleId: String) {
        checkFavorite.execute(scheduleId) { result, err ->
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = result
                error = err?.message
            }
        }
    }

    fun removeFavorite(schedule: Schedule) {
        removeFavorite.execute(scheduleModelToEntityMapper.map(schedule)) { _, err ->
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = false
                error = err?.message
            }
        }
    }

    fun addFavorite(schedule: Schedule) {
        addFavorite.execute(scheduleModelToEntityMapper.map(schedule)) { _, err ->
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = true
                error = err?.message
            }
        }
    }

    companion object {
        const val HOME = "home"
        const val AWAY = "away"
        const val LEAGUE = "league"
        const val FAVORITE = "favorite"
    }
}