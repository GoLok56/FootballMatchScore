package io.github.golok56.footballmatchscore.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.usecase.FindAllLeaguesDetail
import io.github.golok56.domain.usecase.FindAllSoccerLeague
import io.github.golok56.domain.usecase.SaveAllLeagues
import io.github.golok56.footballmatchscore.mapper.LeagueEntityToModelMapper
import io.github.golok56.footballmatchscore.model.League

class MainViewModel(
    private val findAllSoccerLeagues: FindAllSoccerLeague,
    private val findAllLeaguesDetail: FindAllLeaguesDetail,
    private val saveAllLeagues: SaveAllLeagues
) : ViewModel() {
    private var leagues: MutableList<League>? = null
    private var leagueEntityToModelMapper = LeagueEntityToModelMapper()

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
        findAllSoccerLeagues.execute("") { result, err ->
            result?.let {
                if (it[0].logo == null) {
                    findAllLeaguesDetail.execute(it) { leagues, _ ->
                        leagues?.let {
                            val newLeaguesEntity = sortByName(leagues)
                            saveAllLeagues.execute(newLeaguesEntity)

                            this@MainViewModel.leagues = mapToModel(newLeaguesEntity)
                            viewState.value = viewState.value?.copy(
                                loading = false,
                                data = this@MainViewModel.leagues,
                                error = null
                            )
                        }
                    }
                } else {
                    this@MainViewModel.leagues = mapToModel(it)
                    viewState.value = viewState.value?.copy(
                        loading = false,
                        data = this@MainViewModel.leagues,
                        error = null
                    )
                }
            }

            err?.let {
                err.printStackTrace()
                viewState.value = viewState.value?.copy(
                    loading = false,
                    error = it.message,
                    data = null
                )
            }
        }
    }

    private fun sortByName(list: MutableList<LeagueEntity>) =
        list.sortedBy { it.name }.toMutableList()

    private fun mapToModel(list: MutableList<LeagueEntity>) =
        list.map { leagueEntityToModelMapper.map(it) }.toMutableList()

    fun filter(text: String) {
        viewState.value = viewState.value?.copy(
            data = leagues?.filter { it.name?.toLowerCase()?.contains(text.toLowerCase())!! }
                ?.toMutableList()
        )
    }
}