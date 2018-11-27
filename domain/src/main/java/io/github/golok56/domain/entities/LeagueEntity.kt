package io.github.golok56.domain.entities

data class LeagueEntity(
    var id: String?,
    var name: String?,
    var sport: String?,
    var alternateName: String?,
    var logo: String?,
    var updatedOn: Long? = null
)