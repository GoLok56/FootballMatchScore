package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.TeamService
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.Response

class TeamRepository {
    val teams: MutableMap<String, Team?> = mutableMapOf()
    fun findTeams(data: String): Deferred<Response<TeamService.TeamResponse>> {
        return if (teams[data] == null) ApiService.teamService.findTeam(data)
        else async(UI) {
            Response.success(TeamService.TeamResponse(mutableListOf(teams[data])))
        }
    }
}
