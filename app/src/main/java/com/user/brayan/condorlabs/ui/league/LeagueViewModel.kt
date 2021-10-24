package com.user.brayan.condorlabs.ui.league

import androidx.lifecycle.ViewModel
import com.user.brayan.condorlabs.model.Leagues
import javax.inject.Inject

class LeagueViewModel @Inject constructor() : ViewModel() {
    fun loadLeagues(): List<Leagues> {
        val listLeagues: MutableList<Leagues> = mutableListOf()
        listLeagues.add(Leagues(4335, "Spanish La Liga", "Soccer","2021-2022", "Spain", "https://www.thesportsdb.com/images/media/league/badge/7onmyv1534768460.png"))
        listLeagues.add(Leagues(4328, "English Premier League", "Soccer","2021-2022", "England", "https://www.thesportsdb.com/images/media/league/badge/pdd43f1610891709.png"))
        return listLeagues.toList()
    }
}