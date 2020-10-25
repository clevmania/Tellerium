package com.clevmania.tellerium.ui.farmerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clevmania.tellerium.R
import com.clevmania.tellerium.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.farmer_detail_fragment.*

class FarmerDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FarmerDetailFragment()
    }

    private lateinit var viewModel: FarmerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.farmer_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpFarmerDetails.adapter = FarmerDetailsAdapter(this,viewModel)
        TabLayoutMediator(tlFarmerDetails, vpFarmerDetails) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(pos: Int): String? {
        return when (pos) {
            Constants.PERSONAL_PAGE_INDEX -> getString(R.string.personal)
            Constants.IDENTITY_PAGE_INDEX -> getString(R.string.identification)
            Constants.FARM_PAGE_INDEX -> getString(R.string.farm_capture)
            else -> null
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {

        }
    }

}