package io.github.golok56.footballmatchscore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val id: String?,
    val teamId: String?,
    val name: String?,
    val birth: String?,
    val birthLocation: String?,
    val description: String?,
    val position: String?,
    val height: String?,
    val weight: String?,
    val thumbnail: String?,
    val cutout: String?,
    var updatedOn: Long?
) : Parcelable