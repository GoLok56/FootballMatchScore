package io.github.golok56.footballmatchscore.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.golok56.footballmatchscore.usecase.FindAllSoccerLeagues
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val findAllSoccerLeagues: FindAllSoccerLeagues) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(findAllSoccerLeagues) as T
        throw IllegalArgumentException()
    }
}