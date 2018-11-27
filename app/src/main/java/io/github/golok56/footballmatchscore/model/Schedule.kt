package io.github.golok56.footballmatchscore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    val id: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeScore: String?,
    val awayScore: String?,
    val homeId: String?,
    val awayId: String?,
    val date: String?,
    val homeGk: String?,
    val homeDf: String?,
    val homeMf: String?,
    val homeFw: String?,
    val homeSub: String?,
    val awayGk: String?,
    val awayDf: String?,
    val awayMf: String?,
    val awayFw: String?,
    val awaySub: String?,
    val leagueId: String?
) : Parcelable
