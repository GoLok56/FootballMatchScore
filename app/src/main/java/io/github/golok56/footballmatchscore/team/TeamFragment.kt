package io.github.golok56.footballmatchscore.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseApplication
import io.github.golok56.footballmatchscore.league.LeagueActivity
import io.github.golok56.footballmatchscore.main.MainAdapter
import kotlinx.android.synthetic.main.fragment_team.view.*
import org.jetbrains.anko.find
import javax.inject.Inject

class TeamFragment : Fragment() {
    @Inject
    lateinit var factory: TeamViewModelFactory
    private lateinit var adapter: TeamAdapter
    private lateinit var vm: TeamViewModel
    private lateinit var leagueId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_team, container, false).apply {
        adapter = TeamAdapter()
        rvTeam.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueActivity = activity as LeagueActivity
        leagueId = leagueActivity.leagueId
        (leagueActivity.application as BaseApplication)
            .createTeamComponent(leagueId)
            .inject(this)
        leagueActivity.find<Toolbar>(R.id.tbLeague).visibility = View.VISIBLE

        vm = ViewModelProviders.of(leagueActivity, factory)
            .get(TeamViewModel::class.java).apply {
                if (leagueId == MainAdapter.FAVS) fetchFavsTeam() else fetchTeams()
                viewState.observe(this@TeamFragment, Observer(this@TeamFragment::handleState))
            }

        val searchView = leagueActivity.find<SearchView>(R.id.svSchedule)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { vm.filter(newText) }
                return true
            }
        })

        view.swlTeam.setOnRefreshListener {
            if (leagueId == MainAdapter.FAVS) vm.fetchFavsTeam() else vm.fetchTeams()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as BaseApplication).releaseTeamComponent()
    }

    private fun handleState(viewState: TeamViewState?) {
        viewState?.run {
            view?.swlTeam?.isRefreshing = loading

            if (leagueId == MainAdapter.FAVS) data[TeamViewModel.FAVS]?.let { adapter.updateData(it) }
            else data[TeamViewModel.TEAM]?.let { adapter.updateData(it) }

            error?.let { view?.tvTeamError?.text = it }

            view?.tvTeamError?.visibility = if (error == null) View.GONE else View.VISIBLE
            view?.rvTeam?.visibility = if (error == null) View.VISIBLE else View.GONE
        }
    }
}