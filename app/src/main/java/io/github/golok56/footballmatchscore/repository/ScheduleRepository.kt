package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.ScheduleService
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.Response

class ScheduleRepository {
    var nextMatches: MutableMap<String, MutableList<Schedule>?> = mutableMapOf()
    var lastMatches: MutableMap<String, MutableList<Schedule>?> = mutableMapOf()

    fun findAllNextMatches(leagueId: String): Deferred<Response<ScheduleService.ScheduleResponse>> {
        return if (nextMatches[leagueId] == null) ApiService.scheduleService.findNextMatches(leagueId)
        else async(UI) {
            Response.success(ScheduleService.ScheduleResponse(nextMatches[leagueId]))
        }
    }

    fun findAllLastMatches(leagueId: String): Deferred<Response<ScheduleService.ScheduleResponse>> {
        return if (lastMatches[leagueId] == null) ApiService.scheduleService.findLastMatches(leagueId)
        else async(UI) {
            Response.success(ScheduleService.ScheduleResponse(lastMatches[leagueId]))
        }
    }
}