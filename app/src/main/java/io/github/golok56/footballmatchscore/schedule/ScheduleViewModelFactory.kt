package io.github.golok56.footballmatchscore.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.footballmatchscore.usecase.FindAllLastMatches
import io.github.golok56.footballmatchscore.usecase.FindAllNextMatches
import io.github.golok56.footballmatchscore.usecase.FindFavoriteMatches

class ScheduleViewModelFactory(
    private val findAllNextMatches: FindAllNextMatches,
    private val findAllLastMatches: FindAllLastMatches,
    private val findFavoriteMatches: FindFavoriteMatches,
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