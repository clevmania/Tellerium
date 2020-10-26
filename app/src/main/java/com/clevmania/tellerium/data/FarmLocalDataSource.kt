package com.clevmania.tellerium.data

/**
 * @author by Lawrence on 10/26/20.
 * for Tellerium
 */
class FarmLocalDataSource(private val farmDao: FarmDao): FarmDataSource {
    override suspend fun getCapturedFarmById(farmerId: String): FarmEntity {
        return farmDao.getCapturedFarmById(farmerId)
    }

    override suspend fun captureFarm(farm: FarmEntity): Int {
        return farmDao.captureFarm(farm)
    }

    override suspend fun insertFarm(farm: FarmEntity) {
        return farmDao.insertFarm(farm)
    }
}