package com.clevmania.tellerium.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.InjectorUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dash_board_fragment.*

class DashBoardFragment : BaseFragment() {
    private var totalFarmer: Int = 0

    private val viewModel by viewModels<DashBoardViewModel> {
        InjectorUtils.provideDashboardViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dash_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.nav_graph, true).build()
            findNavController().navigate( R.id.loginFragment,null, navOptions)
        }
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