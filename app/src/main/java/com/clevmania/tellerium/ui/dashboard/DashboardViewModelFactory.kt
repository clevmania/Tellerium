package com.clevmania.tellerium.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clevmania.tellerium.data.FarmLocalDataSource
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel

/**
 * @author by Lawrence on 10/26/20.
 * for Tellerium
 */
class DashboardViewModelFactory(
    private val dataSource: FarmerLocalDataSource,
    private val farmDataSource: FarmLocalDataSource
)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashBoardViewModel(farmDataSource,dataSource) as T
    }
}