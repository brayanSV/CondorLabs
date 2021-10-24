package com.user.brayan.condorlabs.di

import com.user.brayan.condorlabs.ui.details_team.DetailsTeamFragment
import com.user.brayan.condorlabs.ui.league.LeagueFragment
import com.user.brayan.condorlabs.ui.team.TeamsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLeagueFragment(): LeagueFragment

    @ContributesAndroidInjector
    abstract fun contributeTeamsFragment(): TeamsFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsTeamsFragment(): DetailsTeamFragment
}