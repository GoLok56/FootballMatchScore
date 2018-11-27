package io.github.golok56.data.mapper

import io.github.golok56.data.entities.PlayerData
import io.github.golok56.domain.entities.PlayerEntity
import io.github.golok56.domain.mapper.Mapper

class PlayerEntityToDataMapper : Mapper<PlayerEntity, PlayerData> {
    override fun map(from: PlayerEntity) = PlayerData(
        from.id, from.teamId, from.name, from.birth, from.birthLocation, from.description,
        from.position, from.height, from.weight, from.thumbnail, from.cutout, from.updatedOn
    )
}