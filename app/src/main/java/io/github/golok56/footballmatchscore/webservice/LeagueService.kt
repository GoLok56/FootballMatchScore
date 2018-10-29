package io.github.golok56.footballmatchscore.webservice

import io.github.golok56.footballmatchscore.model.League
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface LeagueService {
    @GET("all_leagues.php")
    fun findAllLeagues(): Deferred<Response<LeagueResponse>>

    data class LeagueResponse(val leagues: MutableList<League>?)
}