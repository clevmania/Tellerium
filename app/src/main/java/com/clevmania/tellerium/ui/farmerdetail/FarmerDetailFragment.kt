package com.clevmania.tellerium.ui.farmerdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.clevmania.tellerium.R
import com.clevmania.tellerium.TelleriumApp
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.utils.Constants
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.InjectorUtils
import com.clevmania.tellerium.utils.loadImage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.farmer_detail_fragment.*
import javax.inject.Inject

class FarmerDetailFragment : BaseFragment() {
    private val args: FarmerDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: FarmerDetailViewModel

    private val imageUrl by lazy {
        InjectorUtils.getPreference(requireContext()).getImageBaseUrl()
    }

    companion object {
        fun newInstance() = FarmerDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.farmer_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFarmer(args.id)
        vpFarmerDetails.adapter = FarmerDetailsAdapter(this, viewModel)
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
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            farmerInfo.observe(viewLifecycleOwner, EventObserver {
                ivFarmerPassport.loadImage(
                    getString(R.string.farmers_image, imageUrl, it.passport_photo)
                )
                shareFarmerDetail(it)
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TelleriumApp).appComponent.inject(this)
    }

}