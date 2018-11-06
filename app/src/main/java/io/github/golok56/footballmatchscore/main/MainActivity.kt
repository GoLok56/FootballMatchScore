package io.github.golok56.footballmatchscore.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.R.layout.activity_main
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository
import io.github.golok56.footballmatchscore.sqliteservice.getDatabase
import io.github.golok56.footballmatchscore.usecase.FindAllSoccerLeagues
import io.github.golok56.footballmatchscore.usecase.FindLeagueDetail
import io.github.golok56.footballmatchscore.usecase.SaveAllLeagues
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        setSupportActionBar(toolbarMain)

        adapter = MainAdapter(mutableListOf())
        rvMainLeagues.adapter = adapter

        val leagueRepository = LeagueRepository.getInstance(getDatabase())
        val factory = MainViewModelFactory(
            FindAllSoccerLeagues(leagueRepository),
            FindLeagueDetail(leagueRepository),
            SaveAllLeagues(leagueRepository)
        )
        vm = ViewModelProviders.of(this, factory).get(MainViewModel::class.java).apply {
            viewState.observe(this@MainActivity, Observer(this@MainActivity::handleState))
        }

        swlMainLeagues.setOnRefreshListener(vm::fetchData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val search = menu?.findItem(R.id.menu_main_search)?.actionView as SearchView
        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { vm.filter(it) }
                return true
            }
        })
        return true
    }

    private fun handleState(viewState: MainViewState?) {
        handleLoading(viewState?.loading)
        handleData(viewState?.data)
        handleError(viewState?.error)
    }

    private fun handleLoading(loading: Boolean?) {
        loading?.let { swlMainLeagues.isRefreshing = loading }
    }

    private fun handleData(data: MutableList<League>?) {
        data?.let {
            adapter.updateData(data)
            tvMainError.visibility = View.GONE
            rvMainLeagues.visibility = View.VISIBLE
        }
    }

    private fun handleError(error: String?) {
        error?.let {
            tvMainError.text = it
            tvMainError.visibility = View.VISIBLE
            rvMainLeagues.visibility = View.GONE
        }
    }
}