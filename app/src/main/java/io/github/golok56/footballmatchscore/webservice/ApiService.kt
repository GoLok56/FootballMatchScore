package io.github.golok56.footballmatchscore.webservice

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val leagueService: LeagueService = client.create(LeagueService::class.java)
    val scheduleService: ScheduleService = client.create(ScheduleService::class.java)
    val teamService: TeamService = client.create(TeamService::class.java)
}