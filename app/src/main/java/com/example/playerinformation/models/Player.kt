package com.example.playerinformation.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Players(@SerializedName("player") val players: List<Player>?)
data class Player(
    @SerializedName("idPlayer")
    @Expose
    val playerId: String,
    @SerializedName("idTeam")
    @Expose
    val teamName: String,
    @SerializedName("strPlayer")
    @Expose
    val playerName: String,
    @SerializedName("strSport")
    @Expose
    val sport: String,
    @SerializedName("dateBorn")
    @Expose
    val dateBorn: String,
    @SerializedName("strNumber")
    @Expose
    val playerNumber: String,
    @SerializedName("strWage")
    @Expose
    val wage: String,
    @SerializedName("strThumb")
    @Expose
    val thumbNail: String?,
)
