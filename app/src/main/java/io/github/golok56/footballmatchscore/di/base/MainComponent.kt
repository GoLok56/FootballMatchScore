package io.github.golok56.footballmatchscore.di.base

import dagger.Component
import io.github.golok56.footballmatchscore.di.main.MainModule
import io.github.golok56.footballmatchscore.di.main.MainSubComponent
import io.github.golok56.footballmatchscore.di.schedule.ScheduleModule
import io.github.golok56.footballmatchscore.di.schedule.ScheduleSubComponent
import io.github.golok56.footballmatchscore.di.scheduledetail.ScheduleDetailModule
import io.github.golok56.footballmatchscore.di.scheduledetail.ScheduleDetailSubComponent
import io.github.golok56.footballmatchscore.di.team.TeamModule
import io.github.golok56.footballmatchscore.di.team.TeamSubComponent
import io.github.golok56.footballmatchscore.di.teamdetail.TeamDetailModule
import io.github.golok56.footballmatchscore.di.teamdetail.TeamDetailSubComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        MapperModule::class,
        CacheModule::class,
        DataStoreModule::class,
        RepositoryModule::class,
        WebServiceModule::class
    ]
)
interface MainComponent {
    fun plus(mainModule: MainModule): MainSubComponent
    fun plus(scheduleModule: ScheduleModule): ScheduleSubComponent
    fun plus(scheduleDetailModule: ScheduleDetailModule): ScheduleDetailSubComponent
    fun plus(teamModule: TeamModule): TeamSubComponent
    fun plus(teamDetailModule: TeamDetailModule): TeamDetailSubComponent
}