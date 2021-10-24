package com.user.brayan.condorlabs.api

import androidx.lifecycle.LiveData
import com.user.brayan.condorlabs.model.Events
import com.user.brayan.condorlabs.model.Teams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  ApplicationApi {
    @GET("lookup_all_teams.php")
    fun teams(@Query("id") idLeague: Long): LiveData<ApiResponse<List<Teams>>>

    @GET("lookupteam.php")
    fun teamsById(@Query("id") idTeam: Long): LiveData<ApiResponse<Teams>>

    @GET("eventslast.php.php")
    fun teamEvents(@Query("id") idTeam: Long): LiveData<ApiResponse<List<Events>>>
}