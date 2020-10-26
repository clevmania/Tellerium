package com.clevmania.tellerium.data

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * @author by Lawrence on 10/26/20.
 * for Tellerium
 */
@Dao
interface FarmDao {
    @Query("SELECT * FROM farm where farmer_id=:farmerId")
    suspend fun getCapturedFarmById(farmerId : String): FarmEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun captureFarm(farm : FarmEntity) : Int
}