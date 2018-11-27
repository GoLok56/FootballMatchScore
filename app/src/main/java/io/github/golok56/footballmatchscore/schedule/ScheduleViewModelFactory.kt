package io.github.golok56.footballmatchscore.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.domain.usecase.FindAllFavoriteMatches
import io.github.golok56.domain.usecase.FindAllPreviousMatches
import io.github.golok56.domain.usecase.FindAllUpcomingMatches

class ScheduleViewModelFactory(
    private val findAllNextMatches: FindAllUpcomingMatches,
    private val findAllLastMatches: FindAllPreviousMatches,
    private val findFavoriteMatches: FindAllFavoriteMatches,
    private val leagueId: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java))
            return ScheduleViewModel(
                findAllNextMatches,
                findAllLastMatches,
                findFavoriteMatches,
                leagueId
            ) as T
        throw IllegalArgumentException()
    }
}