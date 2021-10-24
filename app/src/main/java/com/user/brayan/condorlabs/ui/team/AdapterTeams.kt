package com.user.brayan.condorlabs.ui.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.databinding.TeamsItemBinding
import com.user.brayan.condorlabs.model.Teams
import com.user.brayan.condorlabs.ui.common.DataBoundListAdapter

class AdapterTeams (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((Teams) -> Unit)?
): DataBoundListAdapter<Teams, TeamsItemBinding> (
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Teams> () {
        override fun areItemsTheSame(oldItem: Teams, newItem: Teams): Boolean {
            return oldItem.idTeam == newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: Teams, newItem: Teams): Boolean {
            return oldItem.idTeam == newItem.idTeam && oldItem.team == newItem.team && oldItem.stadium == newItem.stadium
        }
    }
) {
    override fun createBinding(parent: ViewGroup): TeamsItemBinding {
        val binding = DataBindingUtil.inflate<TeamsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.teams_item,
            parent,
            false,
            dataBindingComponent
        )

        binding.root.setOnClickListener {
            binding.teams?.let {
                callback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: TeamsItemBinding, item: Teams) {
        binding.teams = item
    }

}