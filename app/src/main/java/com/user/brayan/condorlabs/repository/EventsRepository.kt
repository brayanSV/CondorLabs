package com.user.brayan.condorlabs.repository

import androidx.lifecycle.LiveData
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.api.ApiResponse
import com.user.brayan.condorlabs.api.ApplicationApi
import com.user.brayan.condorlabs.db.EventsDao
import com.user.brayan.condorlabs.model.Events
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val eventsDao: EventsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadTeamEvents(idTeam: Long): LiveData<Resource<List<Events>>> = object: NetworkBoundResource<List<Events>, List<Events>>(appExecutors) {
        override fun loadFromDataBase(): LiveData<List<Events>> {
            return eventsDao.load(idTeam)
        }

        override fun shouldFetch(data: List<Events>?): Boolean {
           return data == null || data.isEmpty()
        }

        override fun saveCallResult(item: List<Events>) {
            eventsDao.insert(item)
        }

        override fun createCall(): LiveData<ApiResponse<List<Events>>> {
            return applicationApi.teamEvents(idTeam)
        }
    }.asLiveData()
}