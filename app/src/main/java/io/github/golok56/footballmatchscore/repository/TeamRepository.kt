package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.sqliteservice.DatabaseHelper
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.TeamService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.db.*
import retrofit2.Response
import java.util.*

class TeamRepository private constructor(private val db: DatabaseHelper) : Repository<Team> {
    private val rowParser = object : MapRowParser<Team> {
        override fun parseRow(columns: Map<String, Any?>) = Team(
            id = columns[Team.COLUMN_ID] as String?,
            logo = columns[Team.COLUMN_LOGO] as String?,
            name = columns[Team.COLUMN_NAME] as String?,
            updatedOn = columns[Team.COLUMN_UPDATED_ON] as Long?
        )
    }

    override fun clear() {
        db.use { delete(Team.TABLE_TEAM) }
    }

    override fun save(item: Team) {
        db.use {
            insert(
                Team.TABLE_TEAM,
                Team.COLUMN_ID to item.id,
                Team.COLUMN_NAME to item.name,
                Team.COLUMN_LOGO to item.logo,
                Team.COLUMN_UPDATED_ON to item.updatedOn
            )
        }
    }

    override fun remove(item: Team) {
        item.id?.let {
            db.use { delete(Team.TABLE_TEAM, "(${Team.COLUMN_ID} = {id})", "id" to it) }
        }
    }

    override fun get(id: String) = db.use {
        select(Team.TABLE_TEAM)
            .whereArgs("(${Team.COLUMN_ID} = {id})", "id" to id)
            .exec {
                parseOpt(rowParser)
            }
    }

    override fun getAll() = db.use {
        select(Team.TABLE_TEAM)
            .exec {
                parseList(rowParser).toMutableList()
            }
    }

    override fun isExpired(item: Team) = Date().time - item.updatedOn!! > EXPIRED

    fun findTeam(teamId: String): Deferred<Response<TeamService.TeamResponse>> {
        val team = get(teamId) ?: return ApiService.teamService.findTeam(teamId)
        if (isExpired(team)) return ApiService.teamService.findTeam(teamId)
        return GlobalScope.async(Dispatchers.Main) {
            Response.success(TeamService.TeamResponse(mutableListOf(team)))
        }
    }

    companion object {
        private const val EXPIRED = 30L * 24L * 60L * 60L * 1000L
        private var instance: TeamRepository? = null

        fun getInstance(db: DatabaseHelper): TeamRepository {
            if (instance == null) {
                instance = TeamRepository(db)
            }

            return instance!!
        }
    }
}
