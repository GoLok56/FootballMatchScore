package io.github.golok56.footballmatchscore.di.scheduledetail

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.favorite.FavoriteRepository
import io.github.golok56.data.repository.league.LeagueRepository
import io.github.golok56.data.repository.team.TeamRepository
import io.github.golok56.domain.usecase.*
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailViewModelFactory
import kotlin.coroutines.CoroutineContext

@Module
class ScheduleDetailModule {
    @Provides
    fun providesFindTeam(teamRepository: TeamRepository, dispatchers: CoroutineContext) =
        FindTeam(teamRepository, dispatchers)

    @Provides
    fun providesFindLeagueDetail(
        leagueRepository: LeagueRepository,
        dispatchers: CoroutineContext
    ) =
        FindLeagueDetail(leagueRepository, dispatchers)

    @Provides
    fun providesCheckFavorite(
        favoriteRepository: FavoriteRepository,
        dispatchers: CoroutineContext
    ) =
        CheckFavorite(favoriteRepository, dispatchers)

    @Provides
    fun providesRemoveFavorite(
        favoriteRepository: FavoriteRepository,
        dispatchers: CoroutineContext
    ) =
        RemoveFavorite(favoriteRepository, dispatchers)

    @Provides
    fun providesAddFavorite(favoriteRepository: FavoriteRepository, dispatchers: CoroutineContext) =
        AddFavorite(favoriteRepository, dispatchers)

    @Provides
    fun providesScheduleDetailViewModelFactory(
        findTeam: FindTeam,
        findLeagueDetail: FindLeagueDetail,
        checkFavorite: CheckFavorite,
        removeFavorite: RemoveFavorite,
        addFavorite: AddFavorite
    ) = ScheduleDetailViewModelFactory(
        findTeam,
        findLeagueDetail,
        checkFavorite,
        removeFavorite,
        addFavorite
    )
}