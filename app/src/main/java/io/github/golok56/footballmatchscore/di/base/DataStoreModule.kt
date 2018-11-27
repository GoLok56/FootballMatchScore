package io.github.golok56.footballmatchscore.di.base

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.favorite.FavoriteCache
import io.github.golok56.data.repository.favorite.FavoriteMemoryDataStore
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamCache
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamMemoryDataStore
import io.github.golok56.data.repository.league.LeagueCache
import io.github.golok56.data.repository.league.LeagueMemoryDataStore
import io.github.golok56.data.repository.league.LeagueRemoteDataStore
import io.github.golok56.data.repository.player.PlayerCache
import io.github.golok56.data.repository.player.PlayerMemoryDataStore
import io.github.golok56.data.repository.player.PlayerRemoteDataStore
import io.github.golok56.data.repository.previousschedule.PreviousScheduleCache
import io.github.golok56.data.repository.previousschedule.PreviousScheduleMemoryDataStore
import io.github.golok56.data.repository.previousschedule.PreviousScheduleRemoteDataStore
import io.github.golok56.data.repository.team.TeamCache
import io.github.golok56.data.repository.team.TeamMemoryDataStore
import io.github.golok56.data.repository.team.TeamRemoteDataStore
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleCache
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleMemoryDataStore
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleRemoteDataStore
import io.github.golok56.data.services.LeagueService
import io.github.golok56.data.services.PlayerService
import io.github.golok56.data.services.ScheduleService
import io.github.golok56.data.services.TeamService

@Module
class DataStoreModule {
    @Provides
    fun providesFavoriteMemoryDataStore(favoriteCache: FavoriteCache) =
        FavoriteMemoryDataStore(favoriteCache)

    @Provides
    fun providesLeagueMemoryDataStore(leagueCache: LeagueCache) =
        LeagueMemoryDataStore(leagueCache)

    @Provides
    fun providesLeagueRemoteDataStore(leagueService: LeagueService) =
        LeagueRemoteDataStore(leagueService)

    @Provides
    fun providesPreviousScheduleMemoryDataStore(previousScheduleCache: PreviousScheduleCache) =
        PreviousScheduleMemoryDataStore(previousScheduleCache)

    @Provides
    fun providesPreviousScheduleRemoteDataStore(scheduleService: ScheduleService) =
        PreviousScheduleRemoteDataStore(scheduleService)

    @Provides
    fun providesUpcomingScheduleMemoryDataStore(upcomingSchedule: UpcomingScheduleCache) =
        UpcomingScheduleMemoryDataStore(upcomingSchedule)

    @Provides
    fun providesUpcomingScheduleRemoteDataStore(scheduleService: ScheduleService) =
        UpcomingScheduleRemoteDataStore(scheduleService)

    @Provides
    fun providesTeamMemoryDataStore(teamCache: TeamCache) = TeamMemoryDataStore(teamCache)

    @Provides
    fun providesTeamRemoteDataStore(teamService: TeamService) = TeamRemoteDataStore(teamService)

    @Provides
    fun providesFavoriteTeamMemoryDataStore(favoriteTeamCache: FavoriteTeamCache) =
        FavoriteTeamMemoryDataStore(favoriteTeamCache)

    @Provides
    fun providesPlayerMemoryDataStore(playerCache: PlayerCache) = PlayerMemoryDataStore(playerCache)

    @Provides
    fun providesPlayerRemoteDataStore(playerService: PlayerService) =
        PlayerRemoteDataStore(playerService)
}