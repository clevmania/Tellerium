package com.clevmania.tellerium.ui.update

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clevmania.tellerium.R

class AddFarmFragment : Fragment() {

    companion object {
        fun newInstance() = AddFarmFragment()
    }

    private lateinit var viewModel: AddFarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_farm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddFarmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}