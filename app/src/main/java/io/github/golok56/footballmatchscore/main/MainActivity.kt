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
import io.github.golok56.footballmatchscore.base.BaseApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var adapter: MainAdapter
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        (application as BaseApplication).createLeagueComponent().inject(this)

        setSupportActionBar(toolbarMain)

        adapter = MainAdapter(mutableListOf())
        rvMainLeagues.adapter = adapter

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

    override fun onDestroy() {
        super.onDestroy()
        (application as BaseApplication).releaseLeagueComponent()
    }

    private fun handleState(viewState: MainViewState?) {
        viewState?.loading?.let { swlMainLeagues.isRefreshing = it }
        viewState?.data?.let { adapter.updateData(it) }
        viewState?.error?.let { tvMainError.text = it }

        tvMainError.visibility = if (viewState?.error == null) View.GONE else View.VISIBLE
        rvMainLeagues.visibility = if (viewState?.error == null) View.VISIBLE else View.GONE
    }
}