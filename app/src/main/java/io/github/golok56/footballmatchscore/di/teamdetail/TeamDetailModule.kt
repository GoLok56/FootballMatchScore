package io.github.golok56.footballmatchscore.di.teamdetail

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamRepository
import io.github.golok56.data.repository.player.PlayerRepository
import io.github.golok56.domain.usecase.AddFavoriteTeam
import io.github.golok56.domain.usecase.CheckFavoriteTeam
import io.github.golok56.domain.usecase.FindAllPlayers
import io.github.golok56.domain.usecase.RemoveFavoriteTeam
import io.github.golok56.footballmatchscore.teamdetail.TeamDetailViewModelFactory
import kotlin.coroutines.CoroutineContext

@Module
class TeamDetailModule {
    @Provides
    fun providesFindAllPlayers(
        playerRepository: PlayerRepository,
        dispatchers: CoroutineContext
    ) = FindAllPlayers(playerRepository, dispatchers)

    @Provides
    fun providesCheckFavoriteTeam(
        favoriteRepository: FavoriteTeamRepository,
        dispatchers: CoroutineContext
    ) = CheckFavoriteTeam(favoriteRepository, dispatchers)

    @Provides
    fun providesAddFavoriteTeam(
        favoriteRepository: FavoriteTeamRepository,
        dispatchers: CoroutineContext
    ) = AddFavoriteTeam(favoriteRepository, dispatchers)

    @Provides
    fun providesRemoveFavoriteTeam(
        favoriteRepository: FavoriteTeamRepository,
        dispatchers: CoroutineContext
    ) = RemoveFavoriteTeam(favoriteRepository, dispatchers)

    @Provides
    fun providesTeamDetailViewModelFactory(
        findAllPlayers: FindAllPlayers,
        checkFavoriteTeam: CheckFavoriteTeam,
        addFavoriteTeam: AddFavoriteTeam,
        removeFavoriteTeam: RemoveFavoriteTeam
    ) = TeamDetailViewModelFactory(
        findAllPlayers,
        checkFavoriteTeam,
        addFavoriteTeam,
        removeFavoriteTeam
    )
}