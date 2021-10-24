package com.user.brayan.condorlabs.ui.league

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.databinding.LeagueItemBinding
import com.user.brayan.condorlabs.model.Leagues
import com.user.brayan.condorlabs.ui.common.DataBoundListAdapter

class AdapterLeague (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((Leagues) -> Unit)?
): DataBoundListAdapter<Leagues, LeagueItemBinding>
    (
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Leagues> () {
            override fun areItemsTheSame(oldItem: Leagues, newItem: Leagues): Boolean {
                return oldItem.idLeague == newItem.idLeague
            }

            override fun areContentsTheSame(oldItem: Leagues, newItem: Leagues): Boolean {
                return oldItem.idLeague == newItem.idLeague && oldItem.league == newItem.league && oldItem.sport == newItem.sport && oldItem.currentSeason == newItem.currentSeason && oldItem.country == newItem.country && oldItem.badge == newItem.badge
            }

        }
    )
{
    override fun createBinding(parent: ViewGroup): LeagueItemBinding {
        val binding = DataBindingUtil.inflate<LeagueItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.league_item,
            parent,
            false,
            dataBindingComponent
        )

        binding.root.setOnClickListener {
            binding.leagues?.let {
                callback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: LeagueItemBinding, item: Leagues) {
        binding.leagues = item
    }
}