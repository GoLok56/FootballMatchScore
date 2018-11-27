package io.github.golok56.footballmatchscore.di.base

import dagger.Module
import dagger.Provides
import io.github.golok56.data.mapper.*

@Module
class MapperModule {
    @Provides
    fun providesLeagueDataToEntityMapper() = LeagueDataToEntityMapper()
    @Provides
    fun providesLeagueEntityToDataMapper() = LeagueEntityToDataMapper()
    @Provides
    fun providesScheduleDataToEntityMapper() = ScheduleDataToEntityMapper()
    @Provides
    fun providesScheduleEntityToDataMapper() = ScheduleEntityToDataMapper()
    @Provides
    fun providesTeamDataToEntityMapper() = TeamDataToEntityMapper()
    @Provides
    fun providesTeamEntityToDataMapper() = TeamEntityToDataMapper()
    @Provides
    fun providesPlayerDataToEntityMapper() = PlayerDataToEntityMapper()
    @Provides
    fun providesPlayerEntityToDataMapper() = PlayerEntityToDataMapper()
}