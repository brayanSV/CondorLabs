package com.user.brayan.condorlabs.ui.details_team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.user.brayan.condorlabs.R
import com.user.brayan.condorlabs.di.Injectable

class DetailsTeamFragment : Fragment(), Injectable {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_team_fragment, container, false)
    }

}