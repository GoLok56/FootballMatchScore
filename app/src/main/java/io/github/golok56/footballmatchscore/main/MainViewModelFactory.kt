package io.github.golok56.footballmatchscore.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok56.domain.usecase.FindAllLeaguesDetail
import io.github.golok56.domain.usecase.FindAllSoccerLeague
import io.github.golok56.domain.usecase.SaveAllLeagues

class MainViewModelFactory(
    private val findAllSoccerLeagues: FindAllSoccerLeague,
    private val findAllLeaguesDetail: FindAllLeaguesDetail,
    private val saveAllLeagues: SaveAllLeagues
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(findAllSoccerLeagues, findAllLeaguesDetail, saveAllLeagues) as T
        throw IllegalArgumentException()
    }
}