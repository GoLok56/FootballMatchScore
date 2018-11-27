package io.github.golok56.data.repository.player

import io.github.golok56.data.entities.PlayerData
import io.github.golok56.data.services.DatabaseHelper
import io.github.golok56.domain.repository.Cache
import org.jetbrains.anko.db.*
import java.util.*

class PlayerCache(private val db: DatabaseHelper) : Cache<PlayerData> {
    private val rowParser = object : MapRowParser<PlayerData> {
        override fun parseRow(columns: Map<String, Any?>) = PlayerData(
            id = columns[PlayerData.COLUMN_ID] as String?,
            teamId = columns[PlayerData.COLUMN_TEAM_ID] as String?,
            name = columns[PlayerData.COLUMN_NAME] as String?,
            birth = columns[PlayerData.COLUMN_BIRTH] as String?,
            birthLocation = columns[PlayerData.COLUMN_BIRTH_LOCATION] as String?,
            description = columns[PlayerData.COLUMN_DESCRIPTION] as String?,
            height = columns[PlayerData.COLUMN_HEIGHT] as String?,
            weight = columns[PlayerData.COLUMN_WEIGHT] as String?,
            thumbnail = columns[PlayerData.COLUMN_THUMB] as String?,
            cutout = columns[PlayerData.COLUMN_CUTOUT] as String?,
            updatedOn = columns[PlayerData.COLUMN_UPDATED_ON] as Long?,
            position = columns[PlayerData.COLUMN_POSITION] as String?
        )
    }

    override fun isEmpty() = getAll().isEmpty()

    override fun isExist(item: PlayerData) = if (item.id == null) false else get(item.id) != null

    override fun isExpired(item: PlayerData) =
        if (item.id == null) true
        else Date().time - item.updatedOn!! > EXPIRED

    override fun put(item: PlayerData) = db.use {
        insert(
            PlayerData.TABLE_PLAYER,
            PlayerData.COLUMN_ID to item.id,
            PlayerData.COLUMN_TEAM_ID to item.teamId,
            PlayerData.COLUMN_NAME to item.name,
            PlayerData.COLUMN_BIRTH to item.birth,
            PlayerData.COLUMN_BIRTH_LOCATION to item.birthLocation,
            PlayerData.COLUMN_DESCRIPTION to item.description,
            PlayerData.COLUMN_HEIGHT to item.height,
            PlayerData.COLUMN_WEIGHT to item.weight,
            PlayerData.COLUMN_THUMB to item.thumbnail,
            PlayerData.COLUMN_CUTOUT to item.cutout,
            PlayerData.COLUMN_UPDATED_ON to item.updatedOn,
            PlayerData.COLUMN_POSITION to item.position
        ) != -1L
    }

    override fun get(id: String) = db.use {
        select(PlayerData.TABLE_PLAYER)
            .whereArgs("(${PlayerData.COLUMN_ID} = {id})", "id" to id)
            .exec { parseOpt(rowParser) }
    }

    override fun getAll() = db.use {
        select(PlayerData.TABLE_PLAYER).exec { parseList(rowParser) }.toMutableList()
    }

    override fun clear() {
        db.use { delete(PlayerData.TABLE_PLAYER) }
    }

    override fun remove(item: PlayerData) {
        item.id?.let {
            db.use {
                delete(
                    PlayerData.TABLE_PLAYER,
                    "(${PlayerData.COLUMN_ID} = {id})",
                    "id" to it
                )
            }
        }
    }

    companion object {
        private const val EXPIRED = 30L * 24L * 60L * 60L * 1000L
    }
}