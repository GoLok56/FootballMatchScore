package io.github.golok56.footballmatchscore.scheduledetail

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseApplication
import io.github.golok56.footballmatchscore.base.load
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailFragment.Companion.AWAY
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailFragment.Companion.HOME
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailFragment.Companion.newInstance
import kotlinx.android.synthetic.main.activity_schedule_detail.*
import org.jetbrains.anko.toast
import java.util.*
import javax.inject.Inject

class ScheduleDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ScheduleDetailViewModelFactory

    private lateinit var vm: ScheduleDetailViewModel
    private lateinit var schedule: Schedule

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_detail)
        (application as BaseApplication).createScheduleDetailComponent().inject(this)

        schedule = intent.getParcelableExtra(EXTRA_SCHEDULE)

        vm = ViewModelProviders.of(this, factory)
            .get(ScheduleDetailViewModel::class.java)
            .apply {
                schedule.homeId?.let { find(it, ScheduleDetailViewModel.HOME) }
                schedule.awayId?.let { find(it, ScheduleDetailViewModel.AWAY) }
                schedule.leagueId?.let { getLeague(it) }
                schedule.id?.let { isFavorite(it) }
                viewState.observe(this@ScheduleDetailActivity, Observer { handleState(it) })
            }
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as BaseApplication).releaseScheduleDetailComponent()
    }

    private fun initView() {
        tvScheduleDetailDate.text = schedule.date
        tvScheduleDetailHome.text = schedule.homeTeam
        tvScheduleDetailAway.text = schedule.awayTeam
        tvScheduleDetailHomeScore.text = schedule.homeScore
        tvScheduleDetailAwayScore.text = schedule.awayScore

        tlScheduleDetail.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var fragment = Fragment()
                if (tab?.position == 0) {
                    fragment = newInstance(schedule, HOME)
                } else if (tab?.position == 1) {
                    fragment = newInstance(schedule, AWAY)
                }
                replaceFragment(fragment)
            }
        })
        val fragment = newInstance(schedule, HOME)
        replaceFragment(fragment)

        fabScheduleDetail.setOnClickListener {
            if (isFavorite) {
                vm.removeFavorite(schedule)
            } else {
                vm.addFavorite(schedule)
            }
        }

        tvscheduleDetailAddReminder.setOnClickListener { addReminder() }
    }

    private fun handleState(viewState: ScheduleDetailViewState?) {
        handleData(viewState?.data)
        viewState?.error?.let { toast(it) }
    }

    private fun handleData(data: MutableMap<String, Any?>?) {
        data?.let {
            it[ScheduleDetailViewModel.HOME]?.let { home ->
                if (home is Team) home.logo?.let { logo -> ivScheduleDetailHome.load(logo) }
            }

            it[ScheduleDetailViewModel.AWAY]?.let { away ->
                if (away is Team) away.logo?.let { logo -> ivScheduleDetailAway.load(logo) }
            }

            it[ScheduleDetailViewModel.LEAGUE]?.let { league ->
                if (league is League) league.logo?.let { logo -> ivScheduleDetailLeagueLogo.load(logo) }
            }

            it[ScheduleDetailViewModel.FAVORITE]?.let { isFavorite ->
                if (isFavorite is Boolean) {
                    if (isFavorite) {
                        fabScheduleDetail.setImageResource(R.drawable.ic_star_white_24dp)
                    } else {
                        fabScheduleDetail.setImageResource(R.drawable.ic_star_border_white_24dp)
                    }

                    this.isFavorite = isFavorite
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flScheduleDetail, fragment)
            .commit()
    }

    private fun addReminder() {
        schedule.date?.let {
            val times = it.split("/").map { num -> num.toInt() }
            val beginTime = Calendar.getInstance().run {
                set(2000 + times[2], times[1], times[0])
                timeInMillis
            }
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                .putExtra(CalendarContract.Events.TITLE, "${schedule.homeTeam} vs ${schedule.awayTeam}")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Nonton ${schedule.homeTeam} lawan ${schedule.awayTeam}")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Di rumah")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE)

            startActivity(intent)
        }

        if (schedule.date == null) {
            toast("Tanggal mainnya tidak diketahui")
        }
    }

    companion object {
        const val EXTRA_SCHEDULE = "intent-extra:schedule"
    }
}
