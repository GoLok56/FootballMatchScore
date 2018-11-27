package io.github.golok56.footballmatchscore.mapper

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.mapper.Mapper
import io.github.golok56.footballmatchscore.model.Team

class TeamEntityToModelMapper : Mapper<TeamEntity, Team> {
    override fun map(from: TeamEntity) = Team(
        from.id, from.logo, from.name, from.formedYear, from.stadium, from.description,
        from.leagueId, from.updatedOn
    )
}