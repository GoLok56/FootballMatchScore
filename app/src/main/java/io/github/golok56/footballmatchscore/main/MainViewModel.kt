package io.github.golok56.footballmatchscore.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.golok56.footballmatchscore.usecase.FindAllSoccerLeagues
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainViewModel(private var findAllSoccerLeagues: FindAllSoccerLeagues) : ViewModel() {
    val viewState = MutableLiveData<MainViewState>()

    init {
        viewState.value = MainViewState(true, mutableListOf())
        fetchData(false)
    }

    fun fetchData(refresh: Boolean) {
        viewState.value = viewState.value?.copy(
            loading = true,
            data = null,
            error = null
        )
        launch(UI) {
            try {
                viewState.value = viewState.value?.copy(
                    loading = false,
                    data = findAllSoccerLeagues.execute(refresh),
                    error =  null
                )
            } catch (ex: Exception) {
                viewState.value = viewState.value?.copy(
                    loading = false,
                    error = ex.message,
                    data = null
                )
            }
        }
    }
}