package io.github.golok56.footballmatchscore.di.base

import dagger.Module
import dagger.Provides
import io.github.golok56.data.services.ApiService

@Module
class WebServiceModule {
    @Provides
    fun providesLeagueService() = ApiService.leagueService

    @Provides
    fun providesScheduleService() = ApiService.scheduleService

    @Provides
    fun providesTeamService() = ApiService.teamService

    @Provides
    fun providesPlayerService() = ApiService.playerService
}