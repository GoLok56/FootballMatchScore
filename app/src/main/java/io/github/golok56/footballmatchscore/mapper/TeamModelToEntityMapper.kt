package io.github.golok56.footballmatchscore.mapper

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.mapper.Mapper
import io.github.golok56.footballmatchscore.model.Team

class TeamModelToEntityMapper : Mapper<Team, TeamEntity> {
    override fun map(from: Team) = TeamEntity(
        from.id, from.logo, from.name, from.formedYear, from.stadium, from.description,
        from.leagueId, from.updatedOn
    )
}