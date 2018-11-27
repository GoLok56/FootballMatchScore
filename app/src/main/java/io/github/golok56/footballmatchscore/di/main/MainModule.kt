package io.github.golok56.footballmatchscore.di.main

import dagger.Module
import dagger.Provides
import io.github.golok56.data.repository.league.LeagueRepository
import io.github.golok56.domain.usecase.FindAllLeaguesDetail
import io.github.golok56.domain.usecase.FindAllSoccerLeague
import io.github.golok56.domain.usecase.SaveAllLeagues
import io.github.golok56.footballmatchscore.main.MainViewModelFactory
import kotlin.coroutines.CoroutineContext

@Module
class MainModule {
    @Provides
    fun providesFindAllSoccerLeagues(
        leagueRepository: LeagueRepository,
        dispatchers: CoroutineContext
    ) = FindAllSoccerLeague(leagueRepository, dispatchers)

    @Provides
    fun providesFindAllLeaguesDetail(
        leagueRepository: LeagueRepository,
        dispatchers: CoroutineContext
    ) = FindAllLeaguesDetail(leagueRepository, dispatchers)

    @Provides
    fun providesSaveAllLeagues(
        leagueRepository: LeagueRepository,
        dispatchers: CoroutineContext
    ) = SaveAllLeagues(leagueRepository, dispatchers)

    @Provides
    fun providesMainViewModelFactory(
        findAllSoccerLeague: FindAllSoccerLeague,
        findAllLeaguesDetail: FindAllLeaguesDetail,
        saveAllLeagues: SaveAllLeagues
    ) = MainViewModelFactory(findAllSoccerLeague, findAllLeaguesDetail, saveAllLeagues)
}