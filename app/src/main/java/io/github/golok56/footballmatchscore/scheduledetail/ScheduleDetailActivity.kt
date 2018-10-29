package io.github.golok56.footballmatchscore.scheduledetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.repository.TeamRepository
import io.github.golok56.footballmatchscore.usecase.FindTeam
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class ScheduleDetailActivity : AppCompatActivity() {
    private lateinit var ui: ScheduleDetailActivityUi
    private lateinit var vm: ScheduleDetailViewModel
    private lateinit var schedule: Schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        schedule = intent.getParcelableExtra(EXTRA_SCHEDULE)

        ui = ScheduleDetailActivityUi()
        ui.setContentView(this)

        val factory = ScheduleDetailViewModelFactory(FindTeam(TeamRepository()))
        vm = ViewModelProviders.of(this, factory).get(ScheduleDetailViewModel::class.java).apply {
            schedule.homeId?.let { find(it, ScheduleDetailViewModel.HOME) }
            schedule.awayId?.let { find(it, ScheduleDetailViewModel.AWAY) }
            viewState.observe(this@ScheduleDetailActivity, Observer { handleState(it) })
        }
        initView(schedule)
    }

    private fun initView(schedule: Schedule) {
        ui.date.text = schedule.date
        ui.homeTeam.text = schedule.homeTeam
        ui.awayTeam.text = schedule.awayTeam
        ui.score.text = "${schedule.homeScore?: ""} vs ${schedule.awayScore?: ""}"

        ui.homeGk.text = schedule.homeGk
        ui.homeDf.text = schedule.homeDf
        ui.homeMf.text = schedule.homeMf
        ui.homeFw.text = schedule.homeFw
        ui.homeSub.text = schedule.homeSub

        ui.awayGk.text = schedule.awayGk
        ui.awayDf.text = schedule.awayDf
        ui.awayMf.text = schedule.awayMf
        ui.awayFw.text = schedule.awayFw
        ui.awaySub.text = schedule.awaySub
    }

    private fun handleState(viewState: ScheduleDetailViewState?) {
        handleData(viewState?.data)
        handleError(viewState?.error)
    }

    private fun handleData(data: MutableMap<String, Team?>?) {
        data?.let {
            it[ScheduleDetailViewModel.HOME]?.let {home ->
                Picasso.get().load(home.logo).into(ui.homeLogo)
            }

            it[ScheduleDetailViewModel.AWAY]?.let {away ->
                Picasso.get().load(away.logo).into(ui.awayLogo)
            }
        }
    }

    private fun handleError(error: String?) {
        error?.let { toast(it) }
    }

    companion object {
        const val EXTRA_SCHEDULE = "intent-extra:schedule"
    }
}
