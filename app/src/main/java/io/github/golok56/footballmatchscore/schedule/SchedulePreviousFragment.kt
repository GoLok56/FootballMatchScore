package io.github.golok56.footballmatchscore.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseApplication
import io.github.golok56.footballmatchscore.league.LeagueActivity
import kotlinx.android.synthetic.main.fragment_schedule_list.*
import kotlinx.android.synthetic.main.fragment_schedule_list.view.*
import org.jetbrains.anko.find
import javax.inject.Inject

class SchedulePreviousFragment : androidx.fragment.app.Fragment() {
    @Inject
    lateinit var factory: ScheduleViewModelFactory

    private lateinit var adapter: ScheduleAdapter
    private lateinit var vm: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater
        .inflate(R.layout.fragment_schedule_list, container, false).apply {
            adapter = ScheduleAdapter()
            rvSchedule.adapter = adapter
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val leagueActivity = activity as LeagueActivity
        val leagueId = leagueActivity.leagueId
        (leagueActivity.application as BaseApplication)
            .createScheduleComponent(leagueId)
            .inject(this@SchedulePreviousFragment)
        vm = ViewModelProviders.of(leagueActivity, factory)
            .get(ScheduleViewModel::class.java).apply {
                viewState.observe(
                    this@SchedulePreviousFragment,
                    Observer(this@SchedulePreviousFragment::handleState)
                )
                fetchLastMatch()
            }

        val searchView = leagueActivity.find<SearchView>(R.id.svSchedule)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { vm.filter(newText) }
                return true
            }
        })

        swlSchedule.setOnRefreshListener { vm.fetchLastMatch() }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as BaseApplication).releaseScheduleComponent()
    }

    private fun handleState(viewState: ScheduleViewState?) {
        viewState?.loading?.let { swlSchedule.isRefreshing = it }
        viewState?.data?.get(ScheduleViewModel.LAST)?.let { adapter.updateData(it) }
        viewState?.error?.let { tvScheduleError.text = it }

        tvScheduleError.visibility = if (viewState?.error == null) View.GONE else View.VISIBLE
        rvSchedule.visibility = if (viewState?.error == null) View.VISIBLE else View.GONE
    }
}