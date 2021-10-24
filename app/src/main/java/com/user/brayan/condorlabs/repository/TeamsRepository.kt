package com.user.brayan.condorlabs.repository

import androidx.lifecycle.LiveData
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.api.ApiResponse
import com.user.brayan.condorlabs.api.ApplicationApi
import com.user.brayan.condorlabs.db.TeamsDao
import com.user.brayan.condorlabs.model.Teams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val teamsDao: TeamsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadTeams(idLeague: Long): LiveData<Resource<List<Teams>>> = object: NetworkBoundResource<List<Teams>, List<Teams>>(appExecutors) {
        override fun loadFromDataBase(): LiveData<List<Teams>> {
            return teamsDao.load(idLeague)
        }

        override fun shouldFetch(data: List<Teams>?): Boolean {
            return data == null || data.isEmpty()
        }

        override fun saveCallResult(item: List<Teams>) {
            teamsDao.insert(item)
        }

        override fun createCall(): LiveData<ApiResponse<List<Teams>>> {
            return applicationApi.teams(idLeague)
        }
    }.asLiveData()

    fun loadTeamsById(idTeam: Long): LiveData<Resource<Teams>> = object: NetworkBoundResource<Teams, Teams>(appExecutors) {
        override fun loadFromDataBase(): LiveData<Teams> {
            return teamsDao.loadById(idTeam)
        }

        override fun shouldFetch(data: Teams?): Boolean {
            return data == null
        }

        override fun saveCallResult(item: Teams) {
            teamsDao.insertById(item)
        }

        override fun createCall(): LiveData<ApiResponse<Teams>> {
            return applicationApi.teamsById(idTeam)
        }

    }.asLiveData()
}