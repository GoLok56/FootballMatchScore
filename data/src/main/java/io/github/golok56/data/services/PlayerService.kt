package io.github.golok56.data.services

import io.github.golok56.data.entities.PlayerData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerService {
    @GET("lookup_all_players.php")
    fun findAllPlayer(@Query("id") id: String): Deferred<Response<PlayerResponse>>

    data class PlayerResponse(val player: MutableList<PlayerData>?)
}