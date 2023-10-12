package com.example.playerinformation.models

data class FormerTeams(
    val formerteams: List<FormerTeam>?,
)

data class FormerTeam(
    val id: String?,
    val idFormerTeam: String?,
    val idPlayer: String?,
    val strDeparted: String?,
    val strFormerTeam: String?,
    val strJoined: String?,
    val strMoveType: String?,
    val strPlayer: String?,
    val strSport: String?,
    val strTeamBadge: String?
)