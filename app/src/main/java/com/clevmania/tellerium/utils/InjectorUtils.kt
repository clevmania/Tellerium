package com.clevmania.tellerium.utils

import android.content.Context
import com.clevmania.tellerium.api.FarmerDataService
import com.clevmania.tellerium.api.TelleriumApiService
import com.clevmania.tellerium.data.*
import com.clevmania.tellerium.ui.farmer.FarmerRepository
import com.clevmania.tellerium.ui.farmer.FarmerViewModelFactory
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModelFactory

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */

object InjectorUtils {
    private fun provideService(): FarmerDataService {
        return TelleriumApiService.invoke().create(FarmerDataService::class.java)
    }

    private fun provideFarmerRepository(context: Context): FarmerRepository {
        return FarmerRepository(provideService(), provideFarmerDataSource(context))
    }

    fun provideViewModelFactory(context: Context): FarmerViewModelFactory {
        return FarmerViewModelFactory(provideFarmerRepository(context))
    }

    private fun provideDatabase(context: Context): FarmerDatabase {
        return FarmerDatabase.getInstance(context)
    }

    private fun provideDao(context: Context): FarmerDao {
        return provideDatabase(context).farmerDao()
    }

    private fun provideFarmDao(context: Context): FarmDao {
        return provideDatabase(context).farmDao()
    }

    private fun provideFarmerDataSource(context: Context): FarmerLocalDataSource {
        return FarmerLocalDataSource(provideDao(context))
    }

    private fun provideFarmDataSource(context: Context): FarmLocalDataSource {
        return FarmLocalDataSource(provideFarmDao(context))
    }

    fun provideFarmerDetailViewModelFactory(context: Context): FarmerDetailViewModelFactory {
        return FarmerDetailViewModelFactory(
            provideFarmerDataSource(context), provideFarmDataSource(context)
        )
    }
}
