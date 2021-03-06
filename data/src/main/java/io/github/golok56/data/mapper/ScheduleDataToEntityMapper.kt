package io.github.golok56.data.mapper

import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.mapper.Mapper

class ScheduleDataToEntityMapper : Mapper<ScheduleData, ScheduleEntity> {
    override fun map(from: ScheduleData) = ScheduleEntity(
        from.id, from.homeTeam, from.awayTeam, from.homeScore, from.awayScore, from.homeId,
        from.awayId, from.date, from.homeGk, from.homeDf, from.homeMf, from.homeFw, from.homeSub,
        from.awayGk, from.awayDf, from.awayMf, from.awayFw, from.awaySub, from.leagueId
    )
}