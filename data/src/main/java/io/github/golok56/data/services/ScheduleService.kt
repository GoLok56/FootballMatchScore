package io.github.golok56.data.services

import io.github.golok56.data.entities.ScheduleData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {
    @GET("eventsnextleague.php")
    fun findUpcomingMatches(@Query("id") id: String): Deferred<Response<ScheduleResponse>>

    @GET("eventspastleague.php")
    fun findPreviousMatches(@Query("id") id: String): Deferred<Response<ScheduleResponse>>

    data class ScheduleResponse(val events: MutableList<ScheduleData>?)
}