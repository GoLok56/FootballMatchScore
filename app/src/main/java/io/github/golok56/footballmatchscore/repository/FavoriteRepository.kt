package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.sqliteservice.DatabaseHelper
import kotlinx.coroutines.Deferred
import org.jetbrains.anko.db.*

class FavoriteRepository private constructor(private val db: DatabaseHelper) :
    Repository<Schedule> {
    private val rowParser = object : MapRowParser<Schedule> {
        override fun parseRow(columns: Map<String, Any?>) = Schedule(
            id = columns[Schedule.COLUMN_ID] as String?,
            homeTeam = columns[Schedule.COLUMN_HOME_TEAM] as String?,
            homeScore = columns[Schedule.COLUMN_HOME_SCORE] as String?,
            homeId = columns[Schedule.COLUMN_HOME_ID] as String?,
            homeGk = columns[Schedule.COLUMN_HOME_GK] as String?,
            homeDf = columns[Schedule.COLUMN_HOME_DF] as String?,
            homeMf = columns[Schedule.COLUMN_HOME_MF] as String?,
            homeFw = columns[Schedule.COLUMN_HOME_FW] as String?,
            homeSub = columns[Schedule.COLUMN_HOME_SUB] as String?,
            awayTeam = columns[Schedule.COLUMN_AWAY_TEAM] as String?,
            awayScore = columns[Schedule.COLUMN_AWAY_SCORE] as String?,
            awayId = columns[Schedule.COLUMN_AWAY_ID] as String?,
            awayGk = columns[Schedule.COLUMN_AWAY_GK] as String?,
            awayDf = columns[Schedule.COLUMN_AWAY_DF] as String?,
            awayMf = columns[Schedule.COLUMN_AWAY_MF] as String?,
            awayFw = columns[Schedule.COLUMN_AWAY_FW] as String?,
            awaySub = columns[Schedule.COLUMN_AWAY_SUB] as String?,
            date = columns[Schedule.COLUMN_DATE] as String?,
            leagueId = columns[Schedule.COLUMN_LEAGUE_ID] as String?
        )
    }

    override fun clear() {
        db.use { delete(Schedule.TABLE_FAVORITE) }
    }

    override fun save(item: Schedule) {
        db.use {
            insert(
                Schedule.TABLE_FAVORITE,
                Schedule.COLUMN_ID to item.id,
                Schedule.COLUMN_HOME_TEAM to item.homeTeam,
                Schedule.COLUMN_HOME_SCORE to item.homeScore,
                Schedule.COLUMN_HOME_ID to item.homeId,
                Schedule.COLUMN_HOME_GK to item.homeGk,
                Schedule.COLUMN_HOME_DF to item.homeDf,
                Schedule.COLUMN_HOME_MF to item.homeMf,
                Schedule.COLUMN_HOME_FW to item.homeFw,
                Schedule.COLUMN_HOME_SUB to item.homeSub,
                Schedule.COLUMN_AWAY_TEAM to item.awayTeam,
                Schedule.COLUMN_AWAY_SCORE to item.awayScore,
                Schedule.COLUMN_AWAY_ID to item.awayId,
                Schedule.COLUMN_AWAY_GK to item.awayGk,
                Schedule.COLUMN_AWAY_DF to item.awayDf,
                Schedule.COLUMN_AWAY_MF to item.awayMf,
                Schedule.COLUMN_AWAY_FW to item.awayFw,
                Schedule.COLUMN_AWAY_SUB to item.awaySub,
                Schedule.COLUMN_DATE to item.date,
                Schedule.COLUMN_LEAGUE_ID to item.leagueId
            )
        }
    }

    override fun remove(item: Schedule) {
        item.id?.let {
            db.use { delete(Schedule.TABLE_FAVORITE,"(${Schedule.COLUMN_ID} = {id})", "id" to it) }
        }
    }

    override fun get(id: String) = db.use {
        select(Schedule.TABLE_FAVORITE)
            .whereArgs("(${Schedule.COLUMN_ID} = {id})", "id" to id)
            .exec {
                parseOpt(rowParser)
            }
    }

    override fun getAll() = db.use {
        select(Schedule.TABLE_FAVORITE)
            .exec {
                parseList(rowParser).toMutableList()
            }
    }

    override fun isExpired(item: Schedule) = false

    companion object {
        private var instance: FavoriteRepository? = null
        fun getInstance(db: DatabaseHelper): FavoriteRepository {
            if (instance == null) {
                instance = FavoriteRepository(db)
            }

            return instance!!
        }
    }
}