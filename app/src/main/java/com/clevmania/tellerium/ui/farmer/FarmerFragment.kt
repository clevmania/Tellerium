package com.clevmania.tellerium.ui.farmer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.InjectorUtils
import com.clevmania.tellerium.utils.afterTextChanged
import kotlinx.android.synthetic.main.farmer_fragment.*
import javax.inject.Inject

class FarmerFragment : BaseFragment() {
    private val farmersList = arrayListOf<Farmer>()
    private val adapter: FarmerAdapter = FarmerAdapter(farmersList)
//    private val viewModel by viewModels<FarmerViewModel> {
//        InjectorUtils.provideViewModelFactory(requireContext())
//    }

    @Inject
    lateinit var viewModel: FarmerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.farmer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFarmer.adapter = adapter
        searchFarmers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            allFarmers.observe(viewLifecycleOwner, Observer {
                farmersList.addAll(it)
                adapter.notifyDataSetChanged()
            })
        }
    }

    private fun searchFarmers() {
        tieSearchFarmer.afterTextChanged { query ->
            farmersList.let {
                if (!query.isBlank()) {
                    val searchResult = it.filter { item ->
                        item.first_name.contains(query, ignoreCase = true) ||
                                item.surname.isNotBlank() &&
                                item.surname.contains(query, ignoreCase = true) ||
                                item.lga.contains(query, ignoreCase = true)
                    }
                    adapter.submitData(searchResult)
                } else {
                    adapter.submitData(it)
                }
            }
        }
    }

}