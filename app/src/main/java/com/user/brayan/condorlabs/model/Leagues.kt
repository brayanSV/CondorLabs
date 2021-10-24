package com.user.brayan.condorlabs.model

data class Leagues (
    val idLeague: Long,
    val league: String,
    val sport: String,
    val currentSeason: String,
    val country: String,
    val badge: String
)