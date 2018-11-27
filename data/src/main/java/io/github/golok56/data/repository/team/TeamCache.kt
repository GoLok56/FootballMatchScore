package io.github.golok56.data.repository.team

import io.github.golok56.data.entities.TeamData
import io.github.golok56.data.services.DatabaseHelper
import io.github.golok56.domain.repository.Cache
import org.jetbrains.anko.db.*
import java.util.*

class TeamCache(private val db: DatabaseHelper) : Cache<TeamData> {
    private val rowParser = object : MapRowParser<TeamData> {
        override fun parseRow(columns: Map<String, Any?>) = TeamData(
            id = columns[TeamData.COLUMN_ID] as String?,
            logo = columns[TeamData.COLUMN_LOGO] as String?,
            name = columns[TeamData.COLUMN_NAME] as String?,
            updatedOn = columns[TeamData.COLUMN_UPDATED_ON] as Long?,
            formedYear = columns[TeamData.COLUMN_YEAR] as String?,
            stadium = columns[TeamData.COLUMN_STADIUM] as String?,
            leagueId = columns[TeamData.COLUMN_LEAGUE] as String?,
            description = columns[TeamData.COLUMN_DESCRIPTION] as String?
        )
    }

    override fun isEmpty() = getAll().isEmpty()

    override fun isExist(item: TeamData) = if (item.id == null) false else get(item.id) != null

    override fun isExpired(item: TeamData) =
        if (item.id == null) true
        else Date().time - item.updatedOn!! > EXPIRED

    override fun put(item: TeamData) = db.use {
        insert(
            TeamData.TABLE_TEAM,
            TeamData.COLUMN_ID to item.id,
            TeamData.COLUMN_NAME to item.name,
            TeamData.COLUMN_LOGO to item.logo,
            TeamData.COLUMN_UPDATED_ON to item.updatedOn,
            TeamData.COLUMN_YEAR to item.formedYear,
            TeamData.COLUMN_STADIUM to item.stadium,
            TeamData.COLUMN_LEAGUE to item.leagueId,
            TeamData.COLUMN_DESCRIPTION to item.description
        ) != -1L
    }

    override fun get(id: String) = db.use {
        select(TeamData.TABLE_TEAM)
            .whereArgs("(${TeamData.COLUMN_ID} = {id})", "id" to id)
            .exec { parseOpt(rowParser) }
    }

    override fun getAll() = db.use {
        select(TeamData.TABLE_TEAM).exec { parseList(rowParser).toMutableList() }
    }

    override fun clear() {
        db.use { delete(TeamData.TABLE_TEAM) }
    }

    override fun remove(item: TeamData) {
        item.id?.let {
            db.use {
                delete(
                    TeamData.TABLE_TEAM,
                    "(${TeamData.COLUMN_ID} = {id})",
                    "id" to it
                )
            }
        }
    }

    companion object {
        private const val EXPIRED = 30L * 24L * 60L * 60L * 1000L
    }
}
