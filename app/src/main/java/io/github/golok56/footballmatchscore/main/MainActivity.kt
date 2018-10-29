package io.github.golok56.footballmatchscore.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository
import io.github.golok56.footballmatchscore.usecase.FindAllSoccerLeagues
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    private lateinit var vm: MainViewModel
    private lateinit var ui: MainActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = MainAdapter(this, mutableListOf())
        ui = MainActivityUi(adapter).apply { setContentView(this@MainActivity) }

        val factory = MainViewModelFactory(FindAllSoccerLeagues(LeagueRepository()))
        vm = ViewModelProviders.of(this, factory).get(MainViewModel::class.java).apply {
            viewState.observe(this@MainActivity, Observer { handleState(it) })
        }

        ui.swipeRefreshLayout.setOnRefreshListener { vm.fetchData(true) }

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    private fun handleState(viewState: MainViewState?) {
        handleLoading(viewState?.loading)
        handleData(viewState?.data)
        handleError(viewState?.error)
    }

    private fun handleLoading(loading: Boolean?) {
        loading?.let { ui.swipeRefreshLayout.isRefreshing = loading }
    }

    private fun handleData(data: MutableList<League>?) {
        data?.let {
            adapter.updateData(data)
            ui.error.visibility = View.GONE
            ui.leagueList.visibility = View.VISIBLE
        }
    }

    private fun handleError(error: String?) {
        error?.let {
            ui.error.text = it
            ui.error.visibility = View.VISIBLE
            ui.leagueList.visibility = View.GONE
        }
    }
}