package io.github.golok56.footballmatchscore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    @SerializedName("idEvent") val id: String?,
    @SerializedName("strHomeTeam") val homeTeam: String?,
    @SerializedName("strAwayTeam") val awayTeam: String?,
    @SerializedName("intHomeScore") val homeScore: String?,
    @SerializedName("intAwayScore") val awayScore: String?,
    @SerializedName("idHomeTeam") val homeId: String?,
    @SerializedName("idAwayTeam") val awayId: String?,
    @SerializedName("strDate") val date: String?,
    @SerializedName("strHomeLineupGoalkeeper") val homeGk: String?,
    @SerializedName("strHomeLineupDefense") val homeDf: String?,
    @SerializedName("strHomeLineupMidfield") val homeMf: String?,
    @SerializedName("strHomeLineupForward") val homeFw: String?,
    @SerializedName("strHomeLineupSubstitutes") val homeSub: String?,
    @SerializedName("strAwayLineupGoalkeeper") val awayGk: String?,
    @SerializedName("strAwayLineupDefense") val awayDf: String?,
    @SerializedName("strAwayLineupMidfield") val awayMf: String?,
    @SerializedName("strAwayLineupForward") val awayFw: String?,
    @SerializedName("strAwayLineupSubstitutes") val awaySub: String?,
    @SerializedName("idLeague") val leagueId: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE = "favorite_schedule"
        const val COLUMN_ID = "id"
        const val COLUMN_HOME_ID = "homeId"
        const val COLUMN_HOME_TEAM = "homeTeam"
        const val COLUMN_HOME_SCORE = "homeScore"
        const val COLUMN_HOME_GK = "homeGk"
        const val COLUMN_HOME_DF = "homeDf"
        const val COLUMN_HOME_MF = "homeMf"
        const val COLUMN_HOME_FW = "homeFw"
        const val COLUMN_HOME_SUB = "homeSub"
        const val COLUMN_AWAY_ID = "awayId"
        const val COLUMN_AWAY_TEAM = "awayTeam"
        const val COLUMN_AWAY_SCORE = "awayScore"
        const val COLUMN_AWAY_GK = "awayGk"
        const val COLUMN_AWAY_DF = "awayDf"
        const val COLUMN_AWAY_MF = "awayMf"
        const val COLUMN_AWAY_FW = "awayFw"
        const val COLUMN_AWAY_SUB = "awaySub"
        const val COLUMN_DATE= "date"
        const val COLUMN_LEAGUE_ID= "leagueId"
    }
}
