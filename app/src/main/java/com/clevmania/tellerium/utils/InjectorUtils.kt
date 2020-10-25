package com.clevmania.tellerium.utils

import android.content.Context
import androidx.room.RoomDatabase
import com.clevmania.tellerium.api.FarmerDataService
import com.clevmania.tellerium.api.TelleriumApiService
import com.clevmania.tellerium.data.FarmerDao
import com.clevmania.tellerium.data.FarmerDatabase
import com.clevmania.tellerium.data.FarmerLocalDataSource
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

    private fun provideFarmerRepository(context: Context): FarmerRepository {
        return FarmerRepository(provideService(), provideFarmerDataSource(context))
    }

    fun provideViewModelFactory(context: Context): FarmerViewModelFactory {
        return FarmerViewModelFactory(provideFarmerRepository(context))
    }

    private fun provideDao(context: Context): FarmerDao{
        return FarmerDatabase.getInstance(context).farmerDao()
    }

    fun provideFarmerDataSource(context : Context): FarmerLocalDataSource{
        return FarmerLocalDataSource(provideDao(context))
    }
}
