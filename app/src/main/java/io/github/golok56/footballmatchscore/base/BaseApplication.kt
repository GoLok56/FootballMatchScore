package io.github.golok56.footballmatchscore.base

import android.app.Application
import io.github.golok56.footballmatchscore.di.main.MainModule
import io.github.golok56.footballmatchscore.di.main.MainSubComponent
import io.github.golok56.footballmatchscore.di.base.*
import io.github.golok56.footballmatchscore.di.schedule.ScheduleModule
import io.github.golok56.footballmatchscore.di.schedule.ScheduleSubComponent
import io.github.golok56.footballmatchscore.di.scheduledetail.ScheduleDetailModule
import io.github.golok56.footballmatchscore.di.scheduledetail.ScheduleDetailSubComponent
import io.github.golok56.footballmatchscore.di.team.TeamModule
import io.github.golok56.footballmatchscore.di.team.TeamSubComponent
import io.github.golok56.footballmatchscore.di.teamdetail.TeamDetailModule
import io.github.golok56.footballmatchscore.di.teamdetail.TeamDetailSubComponent

class BaseApplication : Application() {
    private lateinit var mainComponent: MainComponent

    private var leagueSubComponent: MainSubComponent? = null
    private var scheduleSubComponent: ScheduleSubComponent? = null
    private var scheduleDetailSubComponent: ScheduleDetailSubComponent? = null
    private var teamSubComponent: TeamSubComponent? = null
    private var teamDetailSubComponent: TeamDetailSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(this))
            .cacheModule(CacheModule())
            .webServiceModule(WebServiceModule())
            .dataStoreModule(DataStoreModule())
            .mapperModule(MapperModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

    fun createLeagueComponent(): MainSubComponent {
        leagueSubComponent = mainComponent.plus(MainModule())
        return leagueSubComponent!!
    }

    fun releaseLeagueComponent() {
        leagueSubComponent = null
    }

    fun createScheduleComponent(leagueId: String): ScheduleSubComponent {
        scheduleSubComponent = mainComponent.plus(ScheduleModule(leagueId))
        return scheduleSubComponent!!
    }

    fun releaseScheduleComponent() {
        scheduleSubComponent = null
    }

    fun createScheduleDetailComponent(): ScheduleDetailSubComponent {
        scheduleDetailSubComponent = mainComponent.plus(ScheduleDetailModule())
        return scheduleDetailSubComponent!!
    }

    fun releaseScheduleDetailComponent() {
        scheduleDetailSubComponent = null
    }

    fun createTeamComponent(leagueId: String): TeamSubComponent {
        teamSubComponent = mainComponent.plus(TeamModule(leagueId))
        return teamSubComponent!!
    }

    fun releaseTeamComponent() {
        teamSubComponent = null
    }

    fun createTeamDetailComponent(): TeamDetailSubComponent {
        teamDetailSubComponent = mainComponent.plus(TeamDetailModule())
        return teamDetailSubComponent!!
    }

    fun releaseTeamDetailComponent() {
        teamDetailSubComponent = null
    }
}