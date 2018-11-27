package io.github.golok56.footballmatchscore.di.base

import dagger.Module
import dagger.Provides
import io.github.golok56.data.mapper.*
import io.github.golok56.data.repository.favorite.FavoriteMemoryDataStore
import io.github.golok56.data.repository.favorite.FavoriteRepository
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamMemoryDataStore
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamRepository
import io.github.golok56.data.repository.league.LeagueMemoryDataStore
import io.github.golok56.data.repository.league.LeagueRemoteDataStore
import io.github.golok56.data.repository.league.LeagueRepository
import io.github.golok56.data.repository.player.PlayerMemoryDataStore
import io.github.golok56.data.repository.player.PlayerRemoteDataStore
import io.github.golok56.data.repository.player.PlayerRepository
import io.github.golok56.data.repository.previousschedule.PreviousScheduleMemoryDataStore
import io.github.golok56.data.repository.previousschedule.PreviousScheduleRemoteDataStore
import io.github.golok56.data.repository.previousschedule.PreviousScheduleRepository
import io.github.golok56.data.repository.team.TeamMemoryDataStore
import io.github.golok56.data.repository.team.TeamRemoteDataStore
import io.github.golok56.data.repository.team.TeamRepository
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleMemoryDataStore
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleRemoteDataStore
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleRepository

@Module
class RepositoryModule {
    @Provides
    fun providesFavoriteRepository(
        favoriteMemoryDataStore: FavoriteMemoryDataStore,
        scheduleDataToEntityMapper: ScheduleDataToEntityMapper,
        scheduleEntityToDataMapper: ScheduleEntityToDataMapper
    ) = FavoriteRepository(
        favoriteMemoryDataStore,
        scheduleDataToEntityMapper,
        scheduleEntityToDataMapper
    )

    @Provides
    fun providesLeagueRepository(
        leagueMemoryDataStore: LeagueMemoryDataStore,
        leagueRemoteDataStore: LeagueRemoteDataStore,
        leagueDataToEntityMapper: LeagueDataToEntityMapper,
        leagueEntityToDataMapper: LeagueEntityToDataMapper
    ) = LeagueRepository(
        leagueMemoryDataStore,
        leagueRemoteDataStore,
        leagueDataToEntityMapper,
        leagueEntityToDataMapper
    )

    @Provides
    fun providesPreviousScheduleRepository(
        previousScheduleMemoryDataStore: PreviousScheduleMemoryDataStore,
        previousScheduleRemoteDataStore: PreviousScheduleRemoteDataStore,
        scheduleDataToEntityMapper: ScheduleDataToEntityMapper,
        scheduleEntityToDataMapper: ScheduleEntityToDataMapper
    ) = PreviousScheduleRepository(
        previousScheduleMemoryDataStore,
        previousScheduleRemoteDataStore,
        scheduleDataToEntityMapper,
        scheduleEntityToDataMapper
    )

    @Provides
    fun providesTeamRepository(
        teamMemoryDataStore: TeamMemoryDataStore,
        teamRemoteDataStore: TeamRemoteDataStore,
        teamDataToEntityMapper: TeamDataToEntityMapper,
        teamEntityToDataMapper: TeamEntityToDataMapper
    ) = TeamRepository(
        teamMemoryDataStore,
        teamRemoteDataStore,
        teamDataToEntityMapper,
        teamEntityToDataMapper
    )

    @Provides
    fun providesUpcomingScheduleRepository(
        upcomingScheduleMemoryDataStore: UpcomingScheduleMemoryDataStore,
        upcomingScheduleRemoteDataStore: UpcomingScheduleRemoteDataStore,
        scheduleDataToEntityMapper: ScheduleDataToEntityMapper,
        scheduleEntityToDataMapper: ScheduleEntityToDataMapper
    ) = UpcomingScheduleRepository(
        upcomingScheduleMemoryDataStore,
        upcomingScheduleRemoteDataStore,
        scheduleDataToEntityMapper,
        scheduleEntityToDataMapper
    )

    @Provides
    fun providesFavoriteTeamRepository(
        favoriteTeamMemoryDataStore: FavoriteTeamMemoryDataStore,
        teamDataToEntityMapper: TeamDataToEntityMapper,
        teamEntityToDataMapper: TeamEntityToDataMapper
    ) = FavoriteTeamRepository(
        favoriteTeamMemoryDataStore,
        teamDataToEntityMapper,
        teamEntityToDataMapper
    )

    @Provides
    fun providesPlayerRepository(
        playerMemoryDataStore: PlayerMemoryDataStore,
        playerRemoteDataStore: PlayerRemoteDataStore,
        playerDataToEntityMapper: PlayerDataToEntityMapper,
        playerEntityToDataMapper: PlayerEntityToDataMapper
    ) = PlayerRepository(
        playerMemoryDataStore,
        playerRemoteDataStore,
        playerDataToEntityMapper,
        playerEntityToDataMapper
    )
}