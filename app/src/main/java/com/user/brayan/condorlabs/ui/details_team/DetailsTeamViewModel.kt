package com.user.brayan.condorlabs.ui.details_team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.condorlabs.model.Events
import com.user.brayan.condorlabs.model.Teams
import com.user.brayan.condorlabs.repository.EventsRepository
import com.user.brayan.condorlabs.repository.Resource
import com.user.brayan.condorlabs.repository.TeamsRepository
import javax.inject.Inject

class DetailsTeamViewModel @Inject constructor(teamsRepository: TeamsRepository, eventsRepository: EventsRepository) : ViewModel() {
    private val _idTeam: MutableLiveData<Long> = MutableLiveData()
    private val idTeam: LiveData<Long> = _idTeam

    val loadTeamsById: LiveData<Resource<Teams>> = Transformations
        .switchMap(idTeam) {
            teamsRepository.loadTeamsById(it)
        }

    val loadEventsTeams: LiveData<Resource<List<Events>>> = Transformations
        .switchMap(idTeam) {
            eventsRepository.loadTeamEvents(it)
        }

    fun setIdTeam(id: Long?, retry: Boolean) {
        if (retry) {
            _idTeam.value = id
        } else {
            if (id == 0L) {
                return
            }

            if (idTeam.value == id) {
                return
            }

            _idTeam.value = id
        }
    }

    private val _idLeague: MutableLiveData<Long> = MutableLiveData()
    val idLeague: LiveData<Long> = _idLeague

    fun setIdLeague(id: Long?) {
        if (id == 0L) {
            return
        }

        if (idLeague.value == id) {
            return
        }

        _idLeague.value = id
    }

    fun verifyUrl(url: String): String {
        return if (url.contains("https")) {
            url
        } else {
            if (url.contains("www")) {
                "https://$url"
            } else {
                "https://www.$url"
            }
        }
    }

    fun retry() {
        setIdTeam(_idTeam.value, true)
    }
}