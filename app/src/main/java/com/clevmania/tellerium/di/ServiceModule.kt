package com.clevmania.tellerium.di

import com.clevmania.tellerium.api.FarmerDataService
import com.clevmania.tellerium.api.TelleriumApiService
import dagger.Module
import dagger.Provides

/**
 * @author by Lawrence on 10/28/20.
 * for Tellerium
 */
@Module
class ServiceModule {
    @Provides
    fun provideService(): FarmerDataService {
        return TelleriumApiService.invoke().create(FarmerDataService::class.java)
    }
}