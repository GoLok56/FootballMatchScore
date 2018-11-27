package io.github.golok56.domain.entities

data class ScheduleEntity(
    var id: String?,
    var homeTeam: String?,
    var awayTeam: String?,
    var homeScore: String?,
    var awayScore: String?,
    var homeId: String?,
    var awayId: String?,
    var date: String?,
    var homeGk: String?,
    var homeDf: String?,
    var homeMf: String?,
    var homeFw: String?,
    var homeSub: String?,
    var awayGk: String?,
    var awayDf: String?,
    var awayMf: String?,
    var awayFw: String?,
    var awaySub: String?,
    var leagueId: String?
)