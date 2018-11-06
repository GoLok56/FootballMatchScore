package io.github.golok56.footballmatchscore.model

import com.google.gson.annotations.SerializedName

data class League (
    @SerializedName("idLeague") var id: String?,
    @SerializedName("strLeague") var name: String?,
    @SerializedName("strSport") var sport: String?,
    @SerializedName("strLeagueAlternate") var alternateName: String?,
    @SerializedName("strBadge") var logo: String?,
    var updatedOn: Long? = null
) {
    companion object {
        const val TABLE_LEAGUE = "league"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_SPORT = "sport"
        const val COLUMN_ALTERNATE_NAME = "alternate"
        const val COLUMN_LOGO = "logo"
        const val COLUMN_UPDATED_ON = "updated_on"
    }
}