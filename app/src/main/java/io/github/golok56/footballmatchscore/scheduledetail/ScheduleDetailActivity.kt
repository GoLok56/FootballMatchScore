package io.github.golok56.footballmatchscore.scheduledetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.repository.FavoriteRepository
import io.github.golok56.footballmatchscore.repository.LeagueRepository
import io.github.golok56.footballmatchscore.repository.TeamRepository
import io.github.golok56.footballmatchscore.sqliteservice.getDatabase
import io.github.golok56.footballmatchscore.usecase.*
import kotlinx.android.synthetic.main.activity_schedule_detail.*
import org.jetbrains.anko.toast

class ScheduleDetailActivity : AppCompatActivity() {
    private lateinit var vm: ScheduleDetailViewModel
    private lateinit var schedule: Schedule

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_detail)

        schedule = intent.getParcelableExtra(EXTRA_SCHEDULE)

        val db = getDatabase()
        val favoriteRepository = FavoriteRepository.getInstance(db)
        val factory = ScheduleDetailViewModelFactory(
            FindTeam(TeamRepository.getInstance(db)),
            FindLeagueDetail(LeagueRepository.getInstance(db)),
            CheckFavorite(favoriteRepository),
            RemoveFavorite(favoriteRepository),
            AddFavorite(favoriteRepository)
        )
        vm = ViewModelProviders.of(this, factory)
            .get(ScheduleDetailViewModel::class.java)
            .apply {
                schedule.homeId?.let { find(it, ScheduleDetailViewModel.HOME) }
                schedule.awayId?.let { find(it, ScheduleDetailViewModel.AWAY) }
                schedule.leagueId?.let { getLeague(it) }
                schedule.id?.let { isFavorite(it) }
                viewState.observe(this@ScheduleDetailActivity, Observer { handleState(it) })
            }
        initView(schedule)
    }

    private fun initView(schedule: Schedule) {
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
                if (tab?.position == 0) {
                    val fragment = ScheduleDetailFragment.newInstance(
                        schedule,
                        ScheduleDetailFragment.HOME
                    )
                    replaceFragment(fragment)
                } else if (tab?.position == 1) {
                    val fragment = ScheduleDetailFragment.newInstance(
                        schedule,
                        ScheduleDetailFragment.AWAY
                    )
                    replaceFragment(fragment)
                }
            }
        })
        val fragment = ScheduleDetailFragment.newInstance(
            schedule,
            ScheduleDetailFragment.HOME
        )
        replaceFragment(fragment)

        fabScheduleDetail.setOnClickListener {
            if (isFavorite) {
                vm.removeFavorite(schedule)
            } else {
                vm.addFavorite(schedule)
            }
        }
    }

    private fun handleState(viewState: ScheduleDetailViewState?) {
        handleData(viewState?.data)
        handleError(viewState?.error)
    }

    private fun handleData(data: MutableMap<String, Any?>?) {
        data?.let {
            it[ScheduleDetailViewModel.HOME]?.let { home ->
                if (home is Team) {
                    Glide.with(this@ScheduleDetailActivity)
                        .load(home.logo)
                        .into(ivScheduleDetailHome)
                }
            }

            it[ScheduleDetailViewModel.AWAY]?.let { away ->
                if (away is Team) {
                    Glide.with(this@ScheduleDetailActivity)
                        .load(away.logo)
                        .into(ivScheduleDetailAway)
                }
            }

            it[ScheduleDetailViewModel.LEAGUE]?.let { league ->
                if (league is League) {
                    Glide.with(this@ScheduleDetailActivity)
                        .load(league.logo)
                        .into(ivScheduleDetailLeagueLogo)
                }
            }

            it[ScheduleDetailViewModel.FAVORITE]?.let { isFavorite ->
                if (isFavorite is Boolean) {
                    if (isFavorite) {
                        fabScheduleDetail.setImageResource(R.drawable.ic_star_black_24dp)
                    } else {
                        fabScheduleDetail.setImageResource(R.drawable.ic_star_border_black_24dp)
                    }

                    this.isFavorite = isFavorite
                }
            }
        }
    }

    private fun handleError(error: String?) {
        error?.let { toast(it) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flScheduleDetail, fragment)
            .commit()
    }

    companion object {
        const val EXTRA_SCHEDULE = "intent-extra:schedule"
    }
}
