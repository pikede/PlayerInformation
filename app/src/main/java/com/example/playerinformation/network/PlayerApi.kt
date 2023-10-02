package com.example.playerinformation.network

import com.example.playerinformation.models.FormerTeams
import com.example.playerinformation.models.Players
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {
    @GET("searchplayers.php")
    suspend fun getPlayer(@Query("p") playerName: String) : Response<Players>

    @GET("lookupformerteams.php")
    suspend fun getPlayerPreviousTeams(@Query("id") playerId: String) : Response<FormerTeams>
}