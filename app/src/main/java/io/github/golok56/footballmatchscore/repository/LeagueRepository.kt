package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.sqliteservice.DatabaseHelper
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.LeagueService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.db.*
import retrofit2.Response
import java.util.*

class LeagueRepository private constructor(private val db: DatabaseHelper) : Repository<League> {
    private val rowParser = object : MapRowParser<League> {
        override fun parseRow(columns: Map<String, Any?>) = League(
            id = columns[League.COLUMN_ID] as String?,
            name = columns[League.COLUMN_NAME] as String?,
            alternateName = columns[League.COLUMN_ALTERNATE_NAME] as String?,
            sport = columns[League.COLUMN_SPORT] as String?,
            logo = columns[League.COLUMN_LOGO] as String?,
            updatedOn = columns[League.COLUMN_UPDATED_ON] as Long?
        )
    }

    override fun clear() {
        db.use { delete(League.TABLE_LEAGUE) }
    }

    override fun save(item: League) {
        db.use {
            insert(
                League.TABLE_LEAGUE,
                League.COLUMN_ID to item.id,
                League.COLUMN_NAME to item.name,
                League.COLUMN_ALTERNATE_NAME to item.alternateName,
                League.COLUMN_SPORT to item.sport,
                League.COLUMN_LOGO to item.logo,
                League.COLUMN_UPDATED_ON to item.updatedOn
            )
        }
    }

    override fun remove(item: League) {
        item.id?.let {
            db.use { delete(League.TABLE_LEAGUE, "(${League.COLUMN_ID} = {id})", "id" to it) }
        }
    }

    override fun get(id: String) = db.use {
        select(League.TABLE_LEAGUE)
            .whereArgs("(${League.COLUMN_ID} = {id})", "id" to id)
            .exec {
                parseOpt(rowParser)
            }
    }

    override fun getAll() = db.use {
        select(League.TABLE_LEAGUE)
            .exec {
                parseList(rowParser).toMutableList()
            }
    }

    override fun isExpired(item: League) = Date().time - item.updatedOn!! > EXPIRED

    fun findAllLeagues(): Deferred<Response<LeagueService.LeagueResponse>> {
        val leagues = getAll()
        if (leagues.isEmpty() || isExpired(leagues[0])) {
            clear()
            return ApiService.leagueService.findAllLeagues()
        }
        return GlobalScope.async(Dispatchers.Default) {
            Response.success(LeagueService.LeagueResponse(leagues))
        }
    }

    fun findLeague(leagueId: String?): Deferred<Response<LeagueService.LeagueResponse>> {
        leagueId?.let {
            val league = get(leagueId) ?: return ApiService.leagueService.findLeague(leagueId)
            return@findLeague GlobalScope.async(Dispatchers.Main) {
                Response.success(LeagueService.LeagueResponse(mutableListOf(league)))
            }
        }

        throw IllegalArgumentException("leagueId cannot be null")
    }

    companion object {
        private const val EXPIRED = 30L * 24L * 60L * 60L * 1000L
        private var instance: LeagueRepository? = null

        fun getInstance(db: DatabaseHelper): LeagueRepository {
            if (instance == null) {
                instance = LeagueRepository(db)
            }

            return instance!!
        }
    }
}