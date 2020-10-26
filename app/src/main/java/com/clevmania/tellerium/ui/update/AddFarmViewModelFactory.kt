package com.clevmania.tellerium.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clevmania.tellerium.data.FarmLocalDataSource

/**
 * @author by Lawrence on 10/26/20.
 * for Tellerium
 */
class AddFarmViewModelFactory(
    private val farmDataSource: FarmLocalDataSource
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddFarmViewModel(farmDataSource) as T
    }
}