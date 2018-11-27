package io.github.golok56.footballmatchscore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: String?,
    val logo: String?,
    val name: String?,
    val formedYear: String?,
    val stadium: String?,
    val description: String?,
    val leagueId: String?,
    var updatedOn: Long? = null
) : Parcelable