package io.github.golok56.footballmatchscore.schedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.golok56.footballmatchscore.repository.ScheduleRepository
import io.github.golok56.footballmatchscore.usecase.FindAllLastMatches
import io.github.golok56.footballmatchscore.usecase.FindAllNextMatches
import org.jetbrains.anko.setContentView

class ScheduleActivity : AppCompatActivity() {
    lateinit var factory: ScheduleViewModelFactory

    private lateinit var adapter: SchedulePagerAdapter
    private lateinit var ui: ScheduleActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = SchedulePagerAdapter(supportFragmentManager)
        ui = ScheduleActivityUi(adapter).apply { setContentView(this@ScheduleActivity) }

        val scheduleRepository = ScheduleRepository()
        factory = ScheduleViewModelFactory(
            FindAllNextMatches(scheduleRepository),
            FindAllLastMatches(scheduleRepository),
            intent.getStringExtra(EXTRA_LEAGUE_ID)
        )
    }

    companion object {
        const val EXTRA_LEAGUE_ID = "intent-extra:leagueId"
    }
}
