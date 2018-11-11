package io.github.golok56.footballmatchscore.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.main.MainAdapter
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository
import io.github.golok56.footballmatchscore.repository.NextScheduleRepository
import io.github.golok56.footballmatchscore.repository.PreviousScheduleRepository
import io.github.golok56.footballmatchscore.sqliteservice.getDatabase
import io.github.golok56.footballmatchscore.usecase.FindAllLastMatches
import io.github.golok56.footballmatchscore.usecase.FindAllNextMatches
import io.github.golok56.footballmatchscore.usecase.FindFavoriteMatches
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleActivity : AppCompatActivity() {
    lateinit var factory: ScheduleViewModelFactory

    private lateinit var schedulePagerAdapter: SchedulePagerAdapter
    private lateinit var adapter: ScheduleAdapter
    private lateinit var vm: ScheduleViewModel
    private lateinit var leagueId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        leagueId = intent.getStringExtra(EXTRA_LEAGUE_ID)
        factory = ScheduleViewModelFactory(
            FindAllNextMatches(NextScheduleRepository.getInstance()),
            FindAllLastMatches(PreviousScheduleRepository.getInstance()),
            FindFavoriteMatches(FavoriteRepository.getInstance(getDatabase())),
            leagueId
        )

        if (leagueId == MainAdapter.FAVS) {
            initFavoriteView()
            return
        }

        setContentView(R.layout.activity_schedule)

        schedulePagerAdapter = SchedulePagerAdapter(supportFragmentManager)
        vpSchedule.adapter = schedulePagerAdapter
        tlSchedule.setupWithViewPager(vpSchedule)
    }

    private fun initFavoriteView() {
        setContentView(R.layout.fragment_schedule)
        adapter = ScheduleAdapter()
        rvSchedule.adapter = adapter
        vm = ViewModelProviders.of(this, factory).get(ScheduleViewModel::class.java).apply {
            viewState.observe(this@ScheduleActivity, Observer(this@ScheduleActivity::handleState))
            fetchFavoriteMatches()
        }
        swlSchedule.setOnRefreshListener { vm.fetchFavoriteMatches() }
    }

    private fun handleState(viewState: ScheduleViewState?) {
        handleLoading(viewState?.loading)
        handleData(viewState?.data?.get(ScheduleViewModel.FAVS))
        handleError(viewState?.error)

        if (viewState?.data?.get(ScheduleViewModel.FAVS) != null &&
            (viewState.data.get(ScheduleViewModel.FAVS) as MutableList).isEmpty() &&
            !viewState.loading && viewState.error == null
        ) {
            Log.i("Asas", "askalskla")
            handleError("Belum ditemukan pertandingan favorit")
        }
    }

    private fun handleLoading(loading: Boolean?) {
        loading?.let { swlSchedule.isRefreshing = loading }
    }

    private fun handleData(data: MutableList<Schedule>?) {
        data?.let {
            adapter.updateData(it)
            tvScheduleError?.visibility = View.GONE
            rvSchedule?.visibility = View.VISIBLE
        }
    }

    private fun handleError(error: String?) {
        error?.let {
            tvScheduleError?.text = it
            tvScheduleError?.visibility = View.VISIBLE
            rvSchedule?.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_LEAGUE_ID = "intent-extra:leagueId"
    }
}
