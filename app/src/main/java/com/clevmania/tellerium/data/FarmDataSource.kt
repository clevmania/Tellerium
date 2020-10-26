package com.clevmania.tellerium.data

/**
 * @author by Lawrence on 10/26/20.
 * for Tellerium
 */
interface FarmDataSource {
    suspend fun getCapturedFarmById(farmerId: String): FarmEntity

    suspend fun captureFarm(farm: FarmEntity): Int
}