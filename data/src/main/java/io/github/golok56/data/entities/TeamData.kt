package io.github.golok56.data.entities

import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("idTeam") val id: String?,
    @SerializedName("strTeamBadge") val logo: String?,
    @SerializedName("strTeam") val name: String?,
    @SerializedName("intFormedYear") val formedYear: String?,
    @SerializedName("strStadium") val stadium: String?,
    @SerializedName("strDescriptionEN") val description: String?,
    @SerializedName("idLeague") val leagueId: String?,
    var updatedOn: Long? = null
) {
    companion object {
        const val TABLE_TEAM = "team"
        const val TABLE_TEAM_FAVORITE = "team_favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_LOGO = "logo"
        const val COLUMN_NAME = "name"
        const val COLUMN_YEAR = "year"
        const val COLUMN_STADIUM = "stadium"
        const val COLUMN_DESCRIPTION = "desc"
        const val COLUMN_LEAGUE = "leagueId"
        const val COLUMN_UPDATED_ON = "updated_on"
    }
}