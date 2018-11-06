package io.github.golok56.footballmatchscore.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.footballmatchscore.usecase.FindAllSoccerLeagues
import io.github.golok56.footballmatchscore.usecase.FindLeagueDetail
import io.github.golok56.footballmatchscore.usecase.SaveAllLeagues

class MainViewModelFactory(
    private val findAllSoccerLeagues: FindAllSoccerLeagues,
    private val findLeagueDetail: FindLeagueDetail,
    private val saveAllLeagues: SaveAllLeagues
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(findAllSoccerLeagues, findLeagueDetail, saveAllLeagues) as T
        throw IllegalArgumentException()
    }
}