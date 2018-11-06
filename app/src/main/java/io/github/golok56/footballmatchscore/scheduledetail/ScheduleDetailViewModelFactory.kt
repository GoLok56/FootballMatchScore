package io.github.golok56.footballmatchscore.scheduledetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.footballmatchscore.usecase.*
import java.lang.IllegalArgumentException

class ScheduleDetailViewModelFactory(
    private val findTeam: FindTeam,
    private val findLeagueDetail: FindLeagueDetail,
    private val checkFavorite: CheckFavorite,
    private val removeFavorite: RemoveFavorite,
    private val addFavorite: AddFavorite
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleDetailViewModel::class.java))
            return ScheduleDetailViewModel(
                findTeam,
                findLeagueDetail,
                checkFavorite,
                removeFavorite,
                addFavorite
            ) as T
        throw IllegalArgumentException()
    }
}