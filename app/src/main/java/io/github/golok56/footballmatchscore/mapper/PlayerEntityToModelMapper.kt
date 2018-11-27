package io.github.golok56.footballmatchscore.mapper

import io.github.golok56.domain.entities.PlayerEntity
import io.github.golok56.domain.mapper.Mapper
import io.github.golok56.footballmatchscore.model.Player

class PlayerEntityToModelMapper : Mapper<PlayerEntity, Player> {
    override fun map(from: PlayerEntity) = Player(
        from.id, from.teamId, from.name, from.birth, from.birthLocation, from.description,
        from.position, from.height, from.weight, from.thumbnail, from.cutout, from.updatedOn
    )
}