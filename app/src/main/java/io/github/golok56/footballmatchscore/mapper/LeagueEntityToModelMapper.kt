package io.github.golok56.footballmatchscore.mapper

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.mapper.Mapper
import io.github.golok56.footballmatchscore.model.League

class LeagueEntityToModelMapper : Mapper<LeagueEntity, League> {
    override fun map(from: LeagueEntity) = League(
        from.id, from.name, from.sport, from.alternateName, from.logo, from.updatedOn
    )
}