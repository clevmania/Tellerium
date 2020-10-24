package com.clevmania.tellerium.ui.farmer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class FarmerViewModelFactory(private val repository: FarmerRepository)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FarmerViewModel(repository) as T
    }
}