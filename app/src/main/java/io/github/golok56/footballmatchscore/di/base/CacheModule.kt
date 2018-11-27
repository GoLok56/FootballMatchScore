package io.github.golok56.footballmatchscore.di.base

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.favorite.FavoriteCache
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamCache
import io.github.golok56.data.repository.league.LeagueCache
import io.github.golok56.data.repository.player.PlayerCache
import io.github.golok56.data.repository.previousschedule.PreviousScheduleCache
import io.github.golok56.data.repository.team.TeamCache
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleCache
import io.github.golok56.data.services.DatabaseHelper

@Module
class CacheModule {
    @Provides
    fun providesFavoriteCache(db: DatabaseHelper) = FavoriteCache(db)

    @Provides
    fun providesLeagueCache(db: DatabaseHelper) = LeagueCache(db)

    @Provides
    fun providesPreviousScheduleCache() = PreviousScheduleCache()

    @Provides
    fun providesUpcomingScheduleCache() = UpcomingScheduleCache()

    @Provides
    fun providesTeamCache(db: DatabaseHelper) = TeamCache(db)

    @Provides
    fun providesFavoriteTeamCache(db: DatabaseHelper) = FavoriteTeamCache(db)

    @Provides
    fun providesPlayerCache(db: DatabaseHelper) = PlayerCache(db)
}