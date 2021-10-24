package com.user.brayan.condorlabs.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.brayan.condorlabs.ui.details_team.DetailsTeamViewModel
import com.user.brayan.condorlabs.ui.league.LeagueViewModel
import com.user.brayan.condorlabs.ui.team.TeamsViewModel
import com.user.brayan.condorlabs.view_model.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LeagueViewModel::class)
    abstract fun bindLeagueViewModel(leagueViewModel: LeagueViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamsViewModel::class)
    abstract fun bindTeamsViewModell(teamsViewModel: TeamsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsTeamViewModel::class)
    abstract fun bindDetailsTeamViewModel(detailsTeamViewModel: DetailsTeamViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}