package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.LeagueService
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.Response

class LeagueRepository {
    var leagues: MutableList<League>? = null
    fun findAllLeagues(): Deferred<Response<LeagueService.LeagueResponse>> {
        return if (leagues == null) ApiService.leagueService.findAllLeagues()
        else async(UI) { Response.success(LeagueService.LeagueResponse(leagues)) }
    }
}