package io.github.golok56.footballmatchscore.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam") val id: String?,
    @SerializedName("strTeamBadge") val logo: String?,
    @SerializedName("strTeam") val name: String?,
    var updatedOn: Long? = null
) {
    companion object {
        const val TABLE_TEAM = "team"
        const val COLUMN_ID = "id"
        const val COLUMN_LOGO = "logo"
        const val COLUMN_NAME = "name"
        const val COLUMN_UPDATED_ON = "updated_on"
    }
}