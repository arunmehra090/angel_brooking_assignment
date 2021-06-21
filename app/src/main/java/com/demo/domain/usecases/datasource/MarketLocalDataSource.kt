package com.demo.domain.usecases.datasource

import com.demo.data.db.entities.MarketModel

interface MarketLocalDataSource {
    suspend fun saveMarketListToDB(marketList: List<MarketModel>)
    suspend fun deleteMarketListFromDB()
    suspend fun getMarketList(): List<MarketModel>
}