package io.github.golok56.footballmatchscore.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.usecase.FindAllFavoriteMatches
import io.github.golok56.domain.usecase.FindAllPreviousMatches
import io.github.golok56.domain.usecase.FindAllUpcomingMatches
import io.github.golok56.footballmatchscore.mapper.ScheduleEntityToModelMapper
import io.github.golok56.footballmatchscore.model.Schedule

class ScheduleViewModel(
    private val findAllNextMatches: FindAllUpcomingMatches,
    private val findAllPreviousMatches: FindAllPreviousMatches,
    private val findAllFavoriteMatches: FindAllFavoriteMatches,
    private val leagueId: String
) : ViewModel() {
    private var scheduleEntityToModelMapper = ScheduleEntityToModelMapper()
    private var schedules = mutableMapOf<String, MutableList<Schedule>?>()

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

        findAllNextMatches.execute(leagueId) { result, err ->
            updateViewState(result, err, NEXT)
        }
    }

    fun fetchLastMatch() {
        val scheduleViewState = viewState.value?.apply {
            loading = true
            data[LAST] = null
            error = null
        }
        viewState.value = scheduleViewState

        findAllPreviousMatches.execute(leagueId) { result, err ->
            updateViewState(result, err, LAST)
        }
    }

    fun fetchFavoriteMatches() {
        val scheduleViewState = viewState.value?.apply {
            loading = true
            data[FAVS] = null
            error = null
        }
        viewState.value = scheduleViewState

        findAllFavoriteMatches.execute("") { result, err ->
            updateViewState(result, err, FAVS)
        }
    }

    private fun mapToModel(list: MutableList<ScheduleEntity>?) =
        list?.map { scheduleEntityToModelMapper.map(it) }?.toMutableList()

    private fun updateViewState(
        result: MutableList<ScheduleEntity>?,
        err: Exception?,
        which: String
    ) {
        val list = mapToModel(result)
        schedules[which] = list
        viewState.value = viewState.value?.apply {
            loading = false
            data[which] = list
            error = err?.message
        }
    }

    fun filter(text: String) {
        viewState.value = viewState.value?.apply {
            data[NEXT] = schedules[NEXT]?.filter {
                it.homeTeam?.toLowerCase()?.contains(text.toLowerCase())!! ||
                        it.awayTeam?.toLowerCase()?.contains(text.toLowerCase())!!
            }?.toMutableList()
            data[LAST] = schedules[LAST]?.filter {
                it.homeTeam?.toLowerCase()?.contains(text.toLowerCase())!! ||
                        it.awayTeam?.toLowerCase()?.contains(text.toLowerCase())!!
            }?.toMutableList()
            data[FAVS] = schedules[FAVS]?.filter {
                it.homeTeam?.toLowerCase()?.contains(text.toLowerCase())!! ||
                        it.awayTeam?.toLowerCase()?.contains(text.toLowerCase())!!
            }?.toMutableList()
        }
    }

    companion object {
        const val NEXT = "nextMatches"
        const val LAST = "lastMatches"
        const val FAVS = "favoriteMatches"
    }
}
