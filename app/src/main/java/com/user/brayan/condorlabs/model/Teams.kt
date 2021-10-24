package com.user.brayan.condorlabs.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = [
        "idTeam"
    ]
)

data class Teams (
    @field:SerializedName("idTeam")
    val idTeam: Long,
    @field:SerializedName("strTeam")
    val team: String,
    @field:SerializedName("strStadium")
    val stadium: String,
    @field:SerializedName("strDescriptionES")
    val descriptionEs: String?,
    @field:SerializedName("strDescriptionEN")
    val descriptionEn: String?,
    @field:SerializedName("intFormedYear")
    val formedYear: String,
    @field:SerializedName("strTeamBadge")
    val badge: String,
    @field:SerializedName("strTeamJersey")
    val jersey: String?,
    @field:SerializedName("strWebsite")
    val website: String,
    @field:SerializedName("strFacebook")
    val facebook: String,
    @field:SerializedName("strTwitter")
    val twitter: String,
    @field:SerializedName("strInstagram")
    val instagram: String,
    @field:SerializedName("idLeague")
    val idLeague: Long
)