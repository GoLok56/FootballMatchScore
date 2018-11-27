package io.github.golok56.data.mapper

import io.github.golok56.data.entities.LeagueData
import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.mapper.Mapper

class LeagueEntityToDataMapper : Mapper<LeagueEntity, LeagueData> {
    override fun map(from: LeagueEntity) = LeagueData(
        from.id, from.name, from.sport, from.alternateName, from.logo, from.updatedOn
    )
}