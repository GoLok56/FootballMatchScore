package io.github.golok56.footballmatchscore.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.usecase.FindAllLastMatches
import io.github.golok56.footballmatchscore.usecase.FindAllNextMatches
import io.github.golok56.footballmatchscore.usecase.FindFavoriteMatches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val findAllNextMatches: FindAllNextMatches,
    private val findAllLastMatches: FindAllLastMatches,
    private val findFavoriteMatches: FindFavoriteMatches,
    private val leagueId: String
) : ViewModel() {
    val viewState = MutableLiveData<ScheduleViewState>()

    init {
        viewState.value = ScheduleViewState(true, mutableMapOf())
    }

    fun fetchNextMatch() {
        val scheduleViewState = viewState.value?.apply {
            loading = true
            data[NEXT] = null
            error = null
        }
        viewState.value = scheduleViewState

        GlobalScope.launch(Dispatchers.Main) {
            var error: String? = null
            var schedules: MutableList<Schedule>? = null

            try {
                schedules = findAllNextMatches.execute(leagueId)
            } catch (ex: Exception) {
                error = ex.message
            }

            viewState.value = scheduleViewState?.apply {
                loading = false
                data[NEXT] = schedules
                this.error = error
            }
        }
    }

    fun fetchLastMatch() {
        val scheduleViewState = viewState.value?.apply {
            loading = true
            data[LAST] = null
            error = null
        }
        viewState.value = scheduleViewState

        GlobalScope.launch(Dispatchers.Main) {
            var error: String? = null
            var schedules: MutableList<Schedule>? = null

            try {
                schedules = findAllLastMatches.execute(leagueId)
            } catch (ex: Exception) {
                error = ex.message
            }

            viewState.value = scheduleViewState?.apply {
                loading = false
                data[LAST] = schedules
                this.error = error
            }
        }
    }

    fun fetchFavoriteMatches() {
        val scheduleViewState = viewState.value?.apply {
            loading = true
            data[LAST] = null
            error = null
        }
        viewState.value = scheduleViewState

        GlobalScope.launch(Dispatchers.Main) {
            var error: String? = null
            var schedules: MutableList<Schedule>? = null

            try {
                schedules = findFavoriteMatches.execute(true)
            } catch (ex: Exception) {
                error = ex.message
            }

            viewState.value = scheduleViewState?.apply {
                loading = false
                data[LAST] = schedules
                this.error = error
            }
        }
    }

    companion object {
        const val NEXT = "nextMatches"
        const val LAST = "lastMatches"
    }
}
