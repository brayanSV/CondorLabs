package com.user.brayan.condorlabs.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = [
        "idEvent"
    ]
)
data class Events(
    @field:SerializedName("idEvent")
    val idEvent: Long,
    @field:SerializedName("strEvent")
    val nameEvent: String,
    @field:SerializedName("dateEvent")
    val dateEvent: String,
    @field:SerializedName("strTime")
    val timeEvent: String,
    @field:SerializedName("dateEventLocal")
    val dateEventLocal: String?,
    @field:SerializedName("strTimeLocal")
    val timeEventLocal: String?,
    @field:SerializedName("idHomeTeam")
    val idTeam: Long
)
