package io.github.golok56.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleData (
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
        const val TABLE_FAVORITE = "schedule"
        const val COLUMN_ID = "id"
        const val COLUMN_HOME_ID = "home_id"
        const val COLUMN_HOME_TEAM = "home_team"
        const val COLUMN_HOME_SCORE = "home_score"
        const val COLUMN_HOME_GK = "home_gk"
        const val COLUMN_HOME_DF = "home_df"
        const val COLUMN_HOME_MF = "home_mf"
        const val COLUMN_HOME_FW = "home_fw"
        const val COLUMN_HOME_SUB = "home_sub"
        const val COLUMN_AWAY_ID = "away_id"
        const val COLUMN_AWAY_TEAM = "away_team"
        const val COLUMN_AWAY_SCORE = "away_score"
        const val COLUMN_AWAY_GK = "away_gk"
        const val COLUMN_AWAY_DF = "away_df"
        const val COLUMN_AWAY_MF = "away_mf"
        const val COLUMN_AWAY_FW = "away_fw"
        const val COLUMN_AWAY_SUB = "away_sub"
        const val COLUMN_DATE= "date"
        const val COLUMN_LEAGUE_ID= "league_id"
    }
}
