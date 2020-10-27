package com.clevmania.tellerium.ui.update.personal

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clevmania.tellerium.R

class UpdatePersonalFragment : Fragment() {

    companion object {
        fun newInstance() = UpdatePersonalFragment()
    }

    private lateinit var viewModel: UpdatePersonalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.update_personal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UpdatePersonalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}