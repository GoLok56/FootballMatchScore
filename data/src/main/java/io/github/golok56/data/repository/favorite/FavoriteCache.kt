package io.github.golok56.data.repository.favorite

import android.database.SQLException
import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.data.services.DatabaseHelper
import io.github.golok56.domain.repository.Cache
import org.jetbrains.anko.db.*

class FavoriteCache(private val db: DatabaseHelper) : Cache<ScheduleData> {
    private val rowParser = object : MapRowParser<ScheduleData> {
        override fun parseRow(columns: Map<String, Any?>) = ScheduleData(
            id = columns[ScheduleData.COLUMN_ID] as String?,
            homeTeam = columns[ScheduleData.COLUMN_HOME_TEAM] as String?,
            homeScore = columns[ScheduleData.COLUMN_HOME_SCORE] as String?,
            homeId = columns[ScheduleData.COLUMN_HOME_ID] as String?,
            homeGk = columns[ScheduleData.COLUMN_HOME_GK] as String?,
            homeDf = columns[ScheduleData.COLUMN_HOME_DF] as String?,
            homeMf = columns[ScheduleData.COLUMN_HOME_MF] as String?,
            homeFw = columns[ScheduleData.COLUMN_HOME_FW] as String?,
            homeSub = columns[ScheduleData.COLUMN_HOME_SUB] as String?,
            awayTeam = columns[ScheduleData.COLUMN_AWAY_TEAM] as String?,
            awayScore = columns[ScheduleData.COLUMN_AWAY_SCORE] as String?,
            awayId = columns[ScheduleData.COLUMN_AWAY_ID] as String?,
            awayGk = columns[ScheduleData.COLUMN_AWAY_GK] as String?,
            awayDf = columns[ScheduleData.COLUMN_AWAY_DF] as String?,
            awayMf = columns[ScheduleData.COLUMN_AWAY_MF] as String?,
            awayFw = columns[ScheduleData.COLUMN_AWAY_FW] as String?,
            awaySub = columns[ScheduleData.COLUMN_AWAY_SUB] as String?,
            date = columns[ScheduleData.COLUMN_DATE] as String?,
            leagueId = columns[ScheduleData.COLUMN_LEAGUE_ID] as String?
        )
    }

    override fun isEmpty() = getAll().isEmpty()

    override fun isExist(item: ScheduleData): Boolean {
        item.id?.let { return@isExist get(it) != null }
        throw SQLException("Id tidak boleh null")
    }

    override fun isExpired(item: ScheduleData) = false

    override fun put(item: ScheduleData) = db.use {
        insert(
            ScheduleData.TABLE_FAVORITE,
            ScheduleData.COLUMN_ID to item.id,
            ScheduleData.COLUMN_HOME_TEAM to item.homeTeam,
            ScheduleData.COLUMN_HOME_SCORE to item.homeScore,
            ScheduleData.COLUMN_HOME_ID to item.homeId,
            ScheduleData.COLUMN_HOME_GK to item.homeGk,
            ScheduleData.COLUMN_HOME_DF to item.homeDf,
            ScheduleData.COLUMN_HOME_MF to item.homeMf,
            ScheduleData.COLUMN_HOME_FW to item.homeFw,
            ScheduleData.COLUMN_HOME_SUB to item.homeSub,
            ScheduleData.COLUMN_AWAY_TEAM to item.awayTeam,
            ScheduleData.COLUMN_AWAY_SCORE to item.awayScore,
            ScheduleData.COLUMN_AWAY_ID to item.awayId,
            ScheduleData.COLUMN_AWAY_GK to item.awayGk,
            ScheduleData.COLUMN_AWAY_DF to item.awayDf,
            ScheduleData.COLUMN_AWAY_MF to item.awayMf,
            ScheduleData.COLUMN_AWAY_FW to item.awayFw,
            ScheduleData.COLUMN_AWAY_SUB to item.awaySub,
            ScheduleData.COLUMN_DATE to item.date,
            ScheduleData.COLUMN_LEAGUE_ID to item.leagueId
        ) != -1L
    }

    override fun get(id: String) = db.use {
        select(ScheduleData.TABLE_FAVORITE)
            .whereArgs("(${ScheduleData.COLUMN_ID} = {id})", "id" to id)
            .exec { parseOpt(rowParser) }
    }

    override fun getAll() = db.use {
        select(ScheduleData.TABLE_FAVORITE).exec { parseList(rowParser).toMutableList() }
    }

    override fun clear() {
        db.use { delete(ScheduleData.TABLE_FAVORITE) }
    }

    override fun remove(item: ScheduleData) {
        item.id?.let {
            db.use {
                delete(
                    ScheduleData.TABLE_FAVORITE,
                    "(${ScheduleData.COLUMN_ID} = {id})",
                    "id" to it
                )
            }
        }
    }
}
