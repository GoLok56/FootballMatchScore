package io.github.golok56.footballmatchscore.scheduledetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleDetailViewModel(
    private val findTeam: FindTeam,
    private val findLeagueDetail: FindLeagueDetail,
    private val checkFavorite: CheckFavorite,
    private val removeFavorite: RemoveFavorite,
    private val addFavorite: AddFavorite
) : ViewModel() {
    val viewState = MutableLiveData<ScheduleDetailViewState>()

    init {
        viewState.value = ScheduleDetailViewState(false, mutableMapOf())
    }

    fun find(teamId: String, side: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                viewState.value = viewState.value?.apply {
                    data[side] = findTeam.execute(teamId)
                    error = null
                }
            } catch (ex: Exception) {
                viewState.value = viewState.value?.apply {
                    error = ex.message
                    data[side] = null
                }
            }
        }
    }

    fun getLeague(leagueId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                viewState.value = viewState.value?.apply {
                    data[LEAGUE] = findLeagueDetail.execute(leagueId)
                    error = null
                }
            } catch(ex: Exception) {
                viewState.value = viewState.value?.apply {
                    data[LEAGUE] = null
                    error = ex.message
                }
            }
        }
    }

    fun isFavorite(scheduleId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = checkFavorite.execute(scheduleId)
            }
        }
    }

    fun removeFavorite(schedule: Schedule) {
        GlobalScope.launch(Dispatchers.Main) {
            removeFavorite.execute(schedule)
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = false
            }
        }
    }

    fun addFavorite(schedule: Schedule) {
        GlobalScope.launch(Dispatchers.Main) {
            addFavorite.execute(schedule)
            viewState.value = viewState.value?.apply {
                data[FAVORITE] = true
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