package com.clevmania.tellerium.di

import android.content.Context
import com.clevmania.tellerium.data.FarmDao
import com.clevmania.tellerium.data.FarmerDao
import com.clevmania.tellerium.data.FarmerDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author by Lawrence on 10/28/20.
 * for Tellerium
 */
@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): FarmerDatabase {
        return FarmerDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideFarmerDao(database: FarmerDatabase): FarmerDao {
        return database.farmerDao()
    }

    @Singleton
    @Provides
    fun provideFarmDao(database: FarmerDatabase): FarmDao {
        return database.farmDao()
    }
}