package com.clevmania.tellerium.utils

import android.content.Context
import android.content.SharedPreferences
import com.clevmania.tellerium.api.FarmerDataService
import com.clevmania.tellerium.api.TelleriumApiService
import com.clevmania.tellerium.data.*
import com.clevmania.tellerium.ui.dashboard.DashboardViewModelFactory
import com.clevmania.tellerium.ui.farmer.FarmerRepository
import com.clevmania.tellerium.ui.farmer.FarmerViewModelFactory
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModelFactory
import com.clevmania.tellerium.ui.update.AddFarmViewModelFactory
import com.clevmania.tellerium.ui.update.personal.UpdatePersonalViewModelFactory

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */

object InjectorUtils {
    private fun provideService(): FarmerDataService {
        return TelleriumApiService.invoke().create(FarmerDataService::class.java)
    }

    private fun provideFarmerRepository(context: Context): FarmerRepository {
        return FarmerRepository(provideService(),
            provideFarmerDataSource(context), getPreference(context))
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

    fun provideAddFarmViewModelFactory(context: Context): AddFarmViewModelFactory {
        return AddFarmViewModelFactory(provideFarmDataSource(context))
    }

    fun provideDashboardViewModelFactory(context: Context): DashboardViewModelFactory{
        return DashboardViewModelFactory(provideFarmerDataSource(context),
            provideFarmDataSource(context))
    }

    private fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREF_IMAGE_URL, Context.MODE_PRIVATE)
    }

    fun getPreference(context: Context): PreferenceProvider{
        return PreferenceProvider(context)
    }

    fun provideUpdatePersonalViewModelFactory(context: Context) : UpdatePersonalViewModelFactory{
        return UpdatePersonalViewModelFactory(provideFarmerDataSource(context))
    }
}
