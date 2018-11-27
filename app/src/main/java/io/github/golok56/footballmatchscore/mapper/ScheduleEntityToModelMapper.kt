package io.github.golok56.footballmatchscore.mapper

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.mapper.Mapper
import io.github.golok56.footballmatchscore.model.Schedule

class ScheduleEntityToModelMapper : Mapper<ScheduleEntity, Schedule> {
    override fun map(from: ScheduleEntity) = Schedule(
        from.id, from.homeTeam, from.awayTeam, from.homeScore, from.awayScore, from.homeId,
        from.awayId, from.date, from.homeGk, from.homeDf, from.homeMf, from.homeFw, from.homeSub,
        from.awayGk, from.awayDf, from.awayMf, from.awayFw, from.awaySub, from.leagueId
    )
}