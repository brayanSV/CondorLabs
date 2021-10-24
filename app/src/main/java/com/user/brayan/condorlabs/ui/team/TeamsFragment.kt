package com.user.brayan.condorlabs.ui.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.binding.FragmentDataBindingComponent
import com.user.brayan.condorlabs.databinding.TeamsFragmentBinding
import com.user.brayan.condorlabs.di.Injectable
import com.user.brayan.condorlabs.utils.autoCleared
import javax.inject.Inject

class TeamsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<TeamsFragmentBinding>()
    var adapter by autoCleared<AdapterTeams>()

    val teamsViewModel: TeamsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.teams_fragment, container, false, dataBindingComponent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val params = TeamsFragmentArgs.fromBundle(requireArguments())
        teamsViewModel.setIdLeague(params.idLeague)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rvAdapter = AdapterTeams(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) { team ->
            findNavController().navigate(TeamsFragmentDirections.actionNavigationTeamsToDetailsTeamFragment(team.idTeam))
        }

        this.adapter = rvAdapter
        this.binding.teamsList.adapter = this.adapter

        initAccountsList()
    }

    private fun initAccountsList() {
        teamsViewModel.loadTeams.observe(viewLifecycleOwner, Observer { listTeams ->
            binding.teamsResult = teamsViewModel.loadTeams

            if (listTeams?.data != null) {
                adapter.submitList(listTeams.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }
}