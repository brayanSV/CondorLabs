package com.user.brayan.condorlabs.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.condorlabs.model.Teams
import com.user.brayan.condorlabs.repository.Resource
import com.user.brayan.condorlabs.repository.TeamsRepository
import javax.inject.Inject

class TeamsViewModel @Inject constructor(teamsRepository: TeamsRepository) : ViewModel() {
    private val _idLeague: MutableLiveData<Long> = MutableLiveData()
    private val idLeague: LiveData<Long> = _idLeague

    val loadTeams: LiveData<Resource<List<Teams>>> = Transformations
        .switchMap(idLeague) {
            teamsRepository.loadTeams(it)
        }

    fun setIdLeague(id: Long?) {
        if (id == 0L) {
            return
        }

        if (idLeague.value == id) {
            return
        }

        _idLeague.value = id
    }
}