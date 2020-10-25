package com.clevmania.tellerium.ui.farmerdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.ui.farmer.FarmerRepository
import com.clevmania.tellerium.ui.farmer.FarmerViewModel

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
class FarmerDetailViewModelFactory(private val dataSource: FarmerLocalDataSource)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FarmerDetailViewModel(dataSource) as T
    }
}