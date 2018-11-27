package io.github.golok56.domain.entities

data class TeamEntity(
    val id: String?,
    val logo: String?,
    val name: String?,
    val formedYear: String?,
    val stadium: String?,
    val description: String?,
    val leagueId: String?,
    var updatedOn: Long? = null
)
