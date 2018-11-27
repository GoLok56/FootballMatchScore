package io.github.golok56.footballmatchscore.schedule

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
import kotlinx.android.synthetic.main.fragment_schedule_list.*
import org.jetbrains.anko.find
import javax.inject.Inject

class ScheduleFavoriteFragment : Fragment() {
    @Inject
    lateinit var factory: ScheduleViewModelFactory

    private lateinit var adapter: ScheduleAdapter
    private lateinit var vm: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_schedule_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueActivity = activity as LeagueActivity
        val leagueId = leagueActivity.leagueId
        (leagueActivity.application as BaseApplication)
            .createScheduleComponent(leagueId)
            .inject(this)
        leagueActivity.find<Toolbar>(R.id.tbLeague).visibility = View.VISIBLE

        val searchView = leagueActivity.find<SearchView>(R.id.svSchedule)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { vm.filter(newText) }
                return true
            }
        })

        adapter = ScheduleAdapter()
        rvSchedule.adapter = adapter
        vm = ViewModelProviders.of(leagueActivity, factory).get(ScheduleViewModel::class.java)
            .apply {
                viewState.observe(
                    this@ScheduleFavoriteFragment,
                    Observer(this@ScheduleFavoriteFragment::handleState)
                )
                fetchFavoriteMatches()
            }
        swlSchedule.setOnRefreshListener { vm.fetchFavoriteMatches() }

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as BaseApplication).releaseScheduleComponent()
    }

    private fun handleState(viewState: ScheduleViewState?) {
        viewState?.loading?.let { swlSchedule.isRefreshing = it }
        viewState?.data?.get(ScheduleViewModel.FAVS)?.let { adapter.updateData(it) }
        viewState?.error?.let { tvScheduleError?.text = it }

        tvScheduleError?.visibility = if (viewState?.error == null) View.GONE else View.VISIBLE
        rvSchedule?.visibility = if (viewState?.error == null) View.VISIBLE else View.GONE
    }
}