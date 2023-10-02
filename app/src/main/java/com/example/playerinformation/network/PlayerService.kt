package com.example.playerinformation.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlayerService {
    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPlayerApi(): PlayerApi = retrofit.create(PlayerApi::class.java)
}