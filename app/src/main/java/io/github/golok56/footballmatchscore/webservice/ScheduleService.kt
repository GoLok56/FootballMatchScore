package io.github.golok56.footballmatchscore.webservice

import io.github.golok56.footballmatchscore.model.Schedule
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {
    @GET("eventsnextleague.php")
    fun findNextMatches(@Query("id") id: String): Deferred<Response<ScheduleResponse>>

    @GET("eventspastleague.php")
    fun findLastMatches(@Query("id") id: String): Deferred<Response<ScheduleResponse>>

    data class ScheduleResponse(val events: MutableList<Schedule>?)
}