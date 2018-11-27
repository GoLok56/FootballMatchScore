package io.github.golok56.footballmatchscore.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.R.layout.activity_league
import io.github.golok56.footballmatchscore.about.AboutFragment
import io.github.golok56.footballmatchscore.main.MainAdapter
import io.github.golok56.footballmatchscore.schedule.ScheduleFavoriteFragment
import io.github.golok56.footballmatchscore.schedule.ScheduleFragment
import io.github.golok56.footballmatchscore.team.TeamFragment
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : AppCompatActivity() {
    lateinit var leagueId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_league)

        leagueId = intent.getStringExtra(EXTRA_LEAGUE_ID)

        replaceBasedOnLeagueId()
        bnvLeague.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_schedule -> replaceBasedOnLeagueId()
                R.id.menu_bnv_about -> replaceFragment(AboutFragment())
                R.id.menu_bnv_team -> replaceFragment(TeamFragment())
            }
            true
        }
    }

    private fun replaceBasedOnLeagueId() {
        if (leagueId == MainAdapter.FAVS) replaceFragment(ScheduleFavoriteFragment())
        else replaceFragment(ScheduleFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flLeague, fragment)
            .commit()
    }

    companion object {
        const val EXTRA_LEAGUE_ID = "intent-extra:leagueId"
    }
}
