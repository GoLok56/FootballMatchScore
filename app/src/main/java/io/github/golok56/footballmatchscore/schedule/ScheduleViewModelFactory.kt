package io.github.golok56.footballmatchscore.schedule

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.golok56.footballmatchscore.usecase.FindAllLastMatches
import io.github.golok56.footballmatchscore.usecase.FindAllNextMatches
import java.lang.IllegalArgumentException

class ScheduleViewModelFactory(
    private val findAllNextMatches: FindAllNextMatches,
    private val findAllLastMatches: FindAllLastMatches,
    private val leagueId: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java))
            return ScheduleViewModel(findAllNextMatches, findAllLastMatches, leagueId) as T
        throw IllegalArgumentException()
    }
}