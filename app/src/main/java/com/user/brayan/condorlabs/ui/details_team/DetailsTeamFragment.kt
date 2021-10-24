package com.user.brayan.condorlabs.ui.details_team

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.binding.FragmentDataBindingComponent
import com.user.brayan.condorlabs.databinding.DetailsTeamFragmentBinding
import com.user.brayan.condorlabs.di.Injectable
import com.user.brayan.condorlabs.ui.common.RetryCallback
import com.user.brayan.condorlabs.ui.common.SocialNetworksCallback
import com.user.brayan.condorlabs.utils.autoCleared
import java.lang.Exception
import javax.inject.Inject


class DetailsTeamFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<DetailsTeamFragmentBinding>()
    var adapter by autoCleared<AdapterEventsTeam>()

    val detailsTeamsViewModel: DetailsTeamViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.details_team_fragment,
            container,
            false,
            dataBindingComponent
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val params = DetailsTeamFragmentArgs.fromBundle(requireArguments())
        detailsTeamsViewModel.setIdTeam(params.idTeam, false)
        detailsTeamsViewModel.setIdLeague(params.idLeague)
        loadDetailTeam()

        binding.retryCallback = object: RetryCallback {
            override fun retry() {
                detailsTeamsViewModel.retry()
            }
        }

        binding.socialCallback = object: SocialNetworksCallback {
            override fun facebook(url: String) {
                loadSocialNetworks(detailsTeamsViewModel.verifyUrl(url))
            }

            override fun instagram(url: String) {
                loadSocialNetworks(detailsTeamsViewModel.verifyUrl(url))
            }

            override fun twitter(url: String) {
                loadSocialNetworks(detailsTeamsViewModel.verifyUrl(url))
            }

            override fun website(url: String) {
                loadSocialNetworks(detailsTeamsViewModel.verifyUrl(url))
            }
        }
    }

    private fun loadDetailTeam() {
        detailsTeamsViewModel.loadTeamsById.observe(viewLifecycleOwner, Observer { detailTeam ->
            binding.eventsResult = null
            binding.teamsResult = detailsTeamsViewModel.loadTeamsById

            binding.detailTeam = detailTeam.data
        })

        loadEventTeam()
    }

    private fun loadEventTeam() {
        val rvAdapter = AdapterEventsTeam(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        )

        this.adapter = rvAdapter
        this.binding.eventsList.adapter = this.adapter

        initEventTeamList()
    }

    private fun initEventTeamList() {
        detailsTeamsViewModel.loadEventsTeams.observe(viewLifecycleOwner, Observer { eventsTeam ->
            binding.teamsResult = null
            binding.eventsResult = detailsTeamsViewModel.loadEventsTeams

            if (eventsTeam?.data != null) {
                adapter.submitList(eventsTeam.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    private fun loadSocialNetworks(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (ex: Exception) {
            Log.e("error", "${ex.message}")
            Toast.makeText(requireContext(), "No fue posible ver la información", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(DetailsTeamFragmentDirections.actionDetailsTeamFragmentToNavigationTeams(detailsTeamsViewModel.idLeague.value!!))
        }

        return super.onOptionsItemSelected(item)
    }
}