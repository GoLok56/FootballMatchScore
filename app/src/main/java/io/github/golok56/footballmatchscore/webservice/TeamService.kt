package io.github.golok56.footballmatchscore.webservice

import io.github.golok56.footballmatchscore.model.Team
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamService {
    @GET("lookupteam.php")
    fun findTeam(@Query("id") id: String): Deferred<Response<TeamResponse>>

    data class TeamResponse(val teams: MutableList<Team?>)
}