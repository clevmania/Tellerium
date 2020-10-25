package com.clevmania.tellerium.utils

import com.clevmania.tellerium.api.FarmerDataService
import com.clevmania.tellerium.api.TelleriumApiService
import com.clevmania.tellerium.ui.farmer.FarmerRepository
import com.clevmania.tellerium.ui.farmer.FarmerViewModelFactory

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */

object InjectorUtils {
    private fun provideService(): FarmerDataService{
        return TelleriumApiService.invoke().create(FarmerDataService::class.java)
    }

    private fun provideFarmerRepository(): FarmerRepository {
        return FarmerRepository(provideService())
    }

    fun provideViewModelFactory(): FarmerViewModelFactory {
        return FarmerViewModelFactory(provideFarmerRepository())
    }
}
