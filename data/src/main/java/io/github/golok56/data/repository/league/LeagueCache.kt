package io.github.golok56.data.repository.league

import io.github.golok56.data.entities.LeagueData
import io.github.golok56.data.services.DatabaseHelper
import io.github.golok56.domain.repository.Cache
import org.jetbrains.anko.db.*
import java.util.*

class LeagueCache(private val db: DatabaseHelper) : Cache<LeagueData> {
    private val rowParser = object : MapRowParser<LeagueData> {
        override fun parseRow(columns: Map<String, Any?>) = LeagueData(
            id = columns[LeagueData.COLUMN_ID] as String?,
            name = columns[LeagueData.COLUMN_NAME] as String?,
            alternateName = columns[LeagueData.COLUMN_ALTERNATE_NAME] as String?,
            sport = columns[LeagueData.COLUMN_SPORT] as String?,
            logo = columns[LeagueData.COLUMN_LOGO] as String?,
            updatedOn = columns[LeagueData.COLUMN_UPDATED_ON] as Long?
        )
    }

    override fun isExist(item: LeagueData) = if (item.id == null) false else get(item.id!!) != null

    override fun isEmpty() = getAll().isEmpty()

    override fun isExpired(item: LeagueData) =
        if (item.id == null) true
        else Date().time - item.updatedOn!! > EXPIRED

    override fun put(item: LeagueData) = db.use {
        insert(
            LeagueData.TABLE_LEAGUE,
            LeagueData.COLUMN_ID to item.id,
            LeagueData.COLUMN_NAME to item.name,
            LeagueData.COLUMN_ALTERNATE_NAME to item.alternateName,
            LeagueData.COLUMN_SPORT to item.sport,
            LeagueData.COLUMN_LOGO to item.logo,
            LeagueData.COLUMN_UPDATED_ON to item.updatedOn
        ) != -1L
    }

    override fun get(id: String) = db.use {
        select(LeagueData.TABLE_LEAGUE)
            .whereArgs("(${LeagueData.COLUMN_ID} = {id})", "id" to id)
            .exec { parseOpt(rowParser) }
    }

    override fun getAll() = db.use {
        select(LeagueData.TABLE_LEAGUE)
            .exec { parseList(rowParser).toMutableList() }
    }

    override fun clear() {
        db.use { delete(LeagueData.TABLE_LEAGUE) }
    }

    override fun remove(item: LeagueData) {
        item.id?.let {
            db.use {
                delete(
                    LeagueData.TABLE_LEAGUE, "(${LeagueData.COLUMN_ID} = {id})",
                    "id" to it
                )
            }
        }
    }

    companion object {
        private const val EXPIRED = 30L * 24L * 60L * 60L * 1000L
    }
}