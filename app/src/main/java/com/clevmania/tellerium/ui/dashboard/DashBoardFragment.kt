package com.clevmania.tellerium.ui.dashboard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.InjectorUtils
import kotlinx.android.synthetic.main.dash_board_fragment.*

class DashBoardFragment : BaseFragment() {
    private var totalFarmer: Int = 0

    private val viewModel by viewModels<DashBoardViewModel> {
        InjectorUtils.provideDashboardViewModelFactory(requireContext())
    }

    companion object {
        fun newInstance() = DashBoardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dash_board_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            farmCount.observe(viewLifecycleOwner, EventObserver{
                tvUpdatedFarmer.text = it.toString()
                tvPendingCapture.text = it.minus(totalFarmer).toString()
            })

            farmersCount.observe(viewLifecycleOwner, EventObserver{
                totalFarmer = it
                tvTotalFarmerCount.text = it.toString()
            })
        }
    }

}