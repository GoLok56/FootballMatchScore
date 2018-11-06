package io.github.golok56.footballmatchscore.webservice

import io.github.golok56.footballmatchscore.model.League
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueService {
    @GET("all_leagues.php")
    fun findAllLeagues(): Deferred<Response<LeagueResponse>>

    @GET("lookupleague.php")
    fun findLeague(@Query("id") leagueId: String): Deferred<Response<LeagueResponse>>

    data class LeagueResponse(val leagues: MutableList<League>?)
}