package io.github.golok56.footballmatchscore.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.usecase.FindAllSoccerLeagues
import io.github.golok56.footballmatchscore.usecase.FindLeagueDetail
import io.github.golok56.footballmatchscore.usecase.SaveAllLeagues
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val findAllSoccerLeagues: FindAllSoccerLeagues,
    private val findLeagueDetail: FindLeagueDetail,
    private val saveAllLeagues: SaveAllLeagues
) : ViewModel() {
    private var leagues: MutableList<League>? = null
    val viewState = MutableLiveData<MainViewState>()

    init {
        viewState.value = MainViewState(true, mutableListOf())
        fetchData()
    }

    fun fetchData() {
        viewState.value = viewState.value?.copy(
            loading = true,
            data = null,
            error = null
        )
        GlobalScope.launch(Dispatchers.Main) {
            try {
                var leagues = findAllSoccerLeagues.execute(false)
                if (leagues?.get(0)?.logo == null) {
                    leagues = leagues?.map { findLeagueDetail.execute(it.id)!! }
                        ?.sortedBy { it.name }
                        ?.toMutableList()
                    saveAllLeagues.execute(leagues!!)
                }
                this@MainViewModel.leagues = leagues
                viewState.value = viewState.value?.copy(
                    loading = false,
                    data = leagues,
                    error = null
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
                viewState.value = viewState.value?.copy(
                    loading = false,
                    error = ex.message,
                    data = null
                )
            }
        }
    }

    fun filter(text: String) {
        viewState.value = viewState.value?.copy(
            data = leagues?.filter { it.name?.toLowerCase()?.contains(text.toLowerCase())!! }
                ?.toMutableList()
        )
    }
}