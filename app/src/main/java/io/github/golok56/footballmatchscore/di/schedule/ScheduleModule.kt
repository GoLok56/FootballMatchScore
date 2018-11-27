package io.github.golok56.footballmatchscore.di.schedule

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.favorite.FavoriteRepository
import io.github.golok56.data.repository.previousschedule.PreviousScheduleRepository
import io.github.golok56.data.repository.upcomingschedule.UpcomingScheduleRepository
import io.github.golok56.domain.usecase.FindAllFavoriteMatches
import io.github.golok56.domain.usecase.FindAllPreviousMatches
import io.github.golok56.domain.usecase.FindAllUpcomingMatches
import io.github.golok56.footballmatchscore.schedule.ScheduleViewModelFactory
import kotlin.coroutines.CoroutineContext

@Module
class ScheduleModule(private var leagueId: String) {
    @Provides
    fun providesFindAllUpcomingMatches(
        upcomingMatchRepository: UpcomingScheduleRepository,
        dispatchers: CoroutineContext
    ) = FindAllUpcomingMatches(upcomingMatchRepository, dispatchers)

    @Provides
    fun providesFindAllPreviousMatches(
        previousScheduleRepository: PreviousScheduleRepository,
        dispatchers: CoroutineContext
    ) = FindAllPreviousMatches(previousScheduleRepository, dispatchers)

    @Provides
    fun providesAllFavoriteMatches(
        favoriteRepository: FavoriteRepository,
        dispatchers: CoroutineContext
    ) = FindAllFavoriteMatches(favoriteRepository, dispatchers)

    @Provides
    fun providesScheduleViewModelFactory(
        findAllNextMatches: FindAllUpcomingMatches,
        findAllLastMatches: FindAllPreviousMatches,
        findFavoriteMatches: FindAllFavoriteMatches
    ) = ScheduleViewModelFactory(
        findAllNextMatches,
        findAllLastMatches,
        findFavoriteMatches,
        leagueId
    )
}