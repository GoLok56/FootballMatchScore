package io.github.golok56.data.entities

import com.google.gson.annotations.SerializedName

data class PlayerData(
    @SerializedName("idPlayer") val id: String?,
    @SerializedName("idTeam") val teamId: String?,
    @SerializedName("strPlayer") val name: String?,
    @SerializedName("dateBorn") val birth: String?,
    @SerializedName("strBirthLocation") val birthLocation: String?,
    @SerializedName("strDescriptionEN") val description: String?,
    @SerializedName("strPosition") val position: String?,
    @SerializedName("strHeight") val height: String?,
    @SerializedName("strWeight") val weight: String?,
    @SerializedName("strThumb") val thumbnail: String?,
    @SerializedName("strCutout") val cutout: String?,
    var updatedOn: Long?
) {
    companion object {
        const val TABLE_PLAYER = "player"
        const val COLUMN_ID = "id"
        const val COLUMN_TEAM_ID = "teamId"
        const val COLUMN_NAME = "name"
        const val COLUMN_BIRTH = "birth"
        const val COLUMN_BIRTH_LOCATION = "birthLocation"
        const val COLUMN_DESCRIPTION = "desc"
        const val COLUMN_POSITION = "position"
        const val COLUMN_HEIGHT = "height"
        const val COLUMN_WEIGHT = "weight"
        const val COLUMN_THUMB = "thumb"
        const val COLUMN_CUTOUT = "cutout"
        const val COLUMN_UPDATED_ON = "updatedon"
    }
}