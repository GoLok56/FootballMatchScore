package io.github.golok56.domain.entities

data class PlayerEntity(
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
)