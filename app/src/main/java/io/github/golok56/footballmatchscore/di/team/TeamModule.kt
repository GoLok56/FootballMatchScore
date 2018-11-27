package io.github.golok56.footballmatchscore.di.team

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.favoriteteam.FavoriteTeamRepository
import io.github.golok56.data.repository.team.TeamRepository
import io.github.golok56.domain.usecase.FindAllFavoriteTeams
import io.github.golok56.domain.usecase.FindAllTeams
import io.github.golok56.domain.usecase.SaveAllLeagues
import io.github.golok56.domain.usecase.SaveAllTeams
import io.github.golok56.footballmatchscore.team.TeamViewModelFactory
import kotlin.coroutines.CoroutineContext

@Module
class TeamModule(private val leagueId: String) {
    @Provides
    fun providesFindAllTeams(
        teamRepository: TeamRepository,
        dispatchers: CoroutineContext
    ) = FindAllTeams(teamRepository, dispatchers)

    @Provides
    fun providesFindAllFavoriteTeams(
        teamRepository: FavoriteTeamRepository,
        dispatchers: CoroutineContext
    ) = FindAllFavoriteTeams(teamRepository, dispatchers)

    @Provides
    fun providesSaveAllTeams(
        teamRepository: TeamRepository,
        dispatchers: CoroutineContext
    ) = SaveAllTeams(teamRepository, dispatchers)

    @Provides
    fun providesTeamViewModelFdctory(
        findAllTeams: FindAllTeams,
        findAllFavoriteTeams: FindAllFavoriteTeams,
        saveAllTeams: SaveAllTeams
    ) = TeamViewModelFactory(findAllTeams, findAllFavoriteTeams, saveAllTeams, leagueId)
}
