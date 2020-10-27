package com.clevmania.tellerium.ui.update.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clevmania.tellerium.data.FarmerLocalDataSource

/**
 * @author by Lawrence on 10/27/20.
 * for Tellerium
 */
class UpdatePersonalViewModelFactory(
    private val farmerDataSource: FarmerLocalDataSource
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpdatePersonalViewModel(farmerDataSource) as T
    }
}