package com.clevmania.tellerium.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clevmania.lerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.utils.Constants

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
@Database(entities = [Farmer::class], version = 1, exportSchema = false)
abstract class FarmerDatabase : RoomDatabase() {
    abstract fun farmerDao() : FarmerDao

    companion object{
        @Volatile private var instance : FarmerDatabase? = null

        fun getInstance(context: Context): FarmerDatabase {
            return instance
                ?: synchronized(this){
                    instance
                        ?: Room.databaseBuilder(
                            context.applicationContext,
                            FarmerDatabase::class.java, Constants.DATABASE_NAME
                        ).build().also { instance = it }
                }

        }
    }
}