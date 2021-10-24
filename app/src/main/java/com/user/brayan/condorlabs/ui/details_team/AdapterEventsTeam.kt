package com.user.brayan.condorlabs.ui.details_team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.user.brayan.condorlabs.AppExecutors
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.databinding.EventsItemBinding
import com.user.brayan.condorlabs.model.Events
import com.user.brayan.condorlabs.ui.common.DataBoundListAdapter

class AdapterEventsTeam (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
): DataBoundListAdapter<Events, EventsItemBinding> (
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Events> () {
        override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
            return oldItem.idEvent == newItem.idEvent
        }

        override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
            return oldItem.idEvent == newItem.idEvent && oldItem.nameEvent == newItem.nameEvent && oldItem.dateEvent == newItem.dateEvent && oldItem.dateEventLocal == newItem.dateEventLocal  && oldItem.timeEvent == newItem.timeEvent && oldItem.timeEventLocal == newItem.timeEventLocal
        }
    }
) {
    override fun createBinding(parent: ViewGroup): EventsItemBinding {
        val binding = DataBindingUtil.inflate<EventsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.events_item,
            parent,
            false,
            dataBindingComponent
        )

        return binding
    }

    override fun bind(binding: EventsItemBinding, item: Events) {
        binding.eventsTeam = item
    }

}