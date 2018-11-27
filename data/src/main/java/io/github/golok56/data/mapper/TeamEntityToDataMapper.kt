package io.github.golok56.data.mapper

import io.github.golok56.data.entities.TeamData
import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.mapper.Mapper

class TeamEntityToDataMapper : Mapper<TeamEntity, TeamData> {
    override fun map(from: TeamEntity) = TeamData(
        from.id, from.logo, from.name, from.formedYear, from.stadium, from.description,
        from.leagueId, from.updatedOn
    )
}