package com.user.brayan.condorlabs.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.binding.FragmentDataBindingComponent
import com.user.brayan.condorlabs.databinding.LeagueFragmentBinding
import com.user.brayan.condorlabs.di.Injectable
import com.user.brayan.condorlabs.utils.autoCleared
import javax.inject.Inject

class LeagueFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<LeagueFragmentBinding>()
    var adapter by autoCleared<AdapterLeague>()

    val leagueViewModel: LeagueViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.league_fragment, container, false, dataBindingComponent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rvAdapter = AdapterLeague(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) { league ->
            findNavController().navigate(LeagueFragmentDirections.actionNavigationLeagueToTeamsFragment(league.idLeague))
        }

        this.adapter = rvAdapter
        binding.leaguesList.adapter = this.adapter
        adapter.submitList(leagueViewModel.loadLeagues())
    }
}