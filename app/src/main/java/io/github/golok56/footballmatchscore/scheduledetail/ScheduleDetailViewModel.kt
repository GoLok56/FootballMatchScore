package io.github.golok56.footballmatchscore.scheduledetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.golok56.footballmatchscore.usecase.FindTeam
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ScheduleDetailViewModel(private val findTeam: FindTeam) : ViewModel() {
    val viewState = MutableLiveData<ScheduleDetailViewState>()

    init {
        viewState.value = ScheduleDetailViewState(false, mutableMapOf())
    }

    fun find(teamId: String, side: String) {
        launch(UI) {
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

    companion object {
        const val HOME = "home"
        const val AWAY = "away"
    }
}