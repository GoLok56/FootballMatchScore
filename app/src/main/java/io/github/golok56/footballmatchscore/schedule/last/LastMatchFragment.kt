package io.github.golok56.footballmatchscore.schedule.last

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.schedule.ScheduleActivity
import io.github.golok56.footballmatchscore.schedule.ScheduleAdapter
import io.github.golok56.footballmatchscore.schedule.ScheduleViewModel
import io.github.golok56.footballmatchscore.schedule.ScheduleViewState
import org.jetbrains.anko.AnkoContext

class LastMatchFragment : Fragment() {
    private lateinit var adapter: ScheduleAdapter
    private lateinit var vm: ScheduleViewModel
    private lateinit var ui: LastMatchFragmentUi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = ScheduleAdapter(mutableListOf())
        ui = LastMatchFragmentUi(adapter)
        return ui.createView(AnkoContext.createDelegate(container!!))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            vm = ViewModelProviders.of(it, (it as ScheduleActivity).factory)
                .get(ScheduleViewModel::class.java).apply {
                    viewState.observe(this@LastMatchFragment, Observer { state -> handleState(state) })
                    fetchLastMatch()
                }
        }

        ui.swipeRefreshLayout.setOnRefreshListener { vm.fetchLastMatch() }
    }

    private fun handleState(viewState: ScheduleViewState?) {
        handleLoading(viewState?.loading)
        handleData(viewState?.data?.get(ScheduleViewModel.LAST))
        handleError(viewState?.error)
    }

    private fun handleLoading(loading: Boolean?) {
        loading?.let { ui.swipeRefreshLayout.isRefreshing = loading }
    }

    private fun handleData(data: MutableList<Schedule>?) {
        data?.let {
            adapter.updateData(data)
            ui.error.visibility = View.GONE
            ui.matchList.visibility = View.VISIBLE
        }
    }

    private fun handleError(error: String?) {
        error?.let {
            ui.error.text = it
            ui.error.visibility = View.VISIBLE
            ui.matchList.visibility = View.GONE
        }
    }
}