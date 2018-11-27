package io.github.golok56.data.services

import io.github.golok56.data.entities.TeamData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamService {
    @GET("lookupteam.php")
    fun findTeam(@Query("id") id: String): Deferred<Response<TeamResponse>>

    @GET("lookup_all_teams.php")
    fun findAllTeams(@Query("id") id: String): Deferred<Response<TeamResponse>>

    data class TeamResponse(val teams: MutableList<TeamData?>)
}