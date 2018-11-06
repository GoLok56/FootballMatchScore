package io.github.golok56.footballmatchscore.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.model.Schedule
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class SchedulePreviousFragment : androidx.fragment.app.Fragment() {
    private lateinit var adapter: ScheduleAdapter
    private lateinit var vm: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = inflater
            .inflate(R.layout.fragment_schedule, container, false).apply {
                adapter = ScheduleAdapter()
                rvSchedule.adapter = adapter
            }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            vm = ViewModelProviders.of(it, (it as ScheduleActivity).factory)
                    .get(ScheduleViewModel::class.java).apply {
                        viewState.observe(this@SchedulePreviousFragment,
                                Observer(this@SchedulePreviousFragment::handleState))
                        fetchLastMatch()
                    }
        }

        view.swlSchedule.setOnRefreshListener { vm.fetchLastMatch() }
    }

    private fun handleState(viewState: ScheduleViewState?) {
        handleLoading(viewState?.loading)
        handleData(viewState?.data?.get(ScheduleViewModel.LAST))
        handleError(viewState?.error)
    }

    private fun handleLoading(loading: Boolean?) {
        loading?.let { view?.swlSchedule?.isRefreshing = loading }
    }

    private fun handleData(data: MutableList<Schedule>?) {
        data?.let {
            adapter.updateData(it)
            view?.tvScheduleError?.visibility = View.GONE
            view?.rvSchedule?.visibility = View.VISIBLE
        }
    }

    private fun handleError(error: String?) {
        error?.let {
            view?.tvScheduleError?.text = it
            view?.tvScheduleError?.visibility = View.VISIBLE
            view?.rvSchedule?.visibility = View.GONE
        }
    }
}