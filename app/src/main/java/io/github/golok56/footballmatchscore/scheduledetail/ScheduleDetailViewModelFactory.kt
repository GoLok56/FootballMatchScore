package io.github.golok56.footballmatchscore.scheduledetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.golok56.footballmatchscore.usecase.FindTeam
import java.lang.IllegalArgumentException

class ScheduleDetailViewModelFactory(private val findTeam: FindTeam) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleDetailViewModel::class.java))
            return ScheduleDetailViewModel(findTeam) as T
        throw IllegalArgumentException()
    }
}