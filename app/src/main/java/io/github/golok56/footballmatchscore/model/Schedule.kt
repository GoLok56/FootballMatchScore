package io.github.golok56.footballmatchscore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    @SerializedName("idEvent")
    val id: String?,
    @SerializedName("strHomeTeam")
    val homeTeam: String?,
    @SerializedName("strAwayTeam")
    val awayTeam: String?,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,
    @SerializedName("idHomeTeam")
    val homeId: String?,
    @SerializedName("idAwayTeam")
    val awayId: String?,
    @SerializedName("strDate")
    val date: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    val homeGk: String?,
    @SerializedName("strHomeLineupDefense")
    val homeDf: String?,
    @SerializedName("strHomeLineupMidfield")
    val homeMf: String?,
    @SerializedName("strHomeLineupForward")
    val homeFw: String?,
    @SerializedName("strHomeLineupSubstitutes")
    val homeSub: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    val awayGk: String?,
    @SerializedName("strAwayLineupDefense")
    val awayDf: String?,
    @SerializedName("strAwayLineupMidfield")
    val awayMf: String?,
    @SerializedName("strAwayLineupForward")
    val awayFw: String?,
    @SerializedName("strAwayLineupSubstitutes")
    val awaySub: String?
) : Parcelable