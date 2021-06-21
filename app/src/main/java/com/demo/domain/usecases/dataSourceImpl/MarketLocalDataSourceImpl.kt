package com.demo.domain.usecases.dataSourceImpl

import com.demo.data.db.daos.MarketDao
import com.demo.data.db.entities.MarketModel
import com.demo.domain.usecases.datasource.MarketLocalDataSource

class MarketLocalDataSourceImpl(private val marketDao: MarketDao): MarketLocalDataSource {
    override suspend fun saveMarketListToDB(marketList: List<MarketModel>) {
        marketDao.insertMarketData(marketList)
    }

    override suspend fun deleteMarketListFromDB() {
        marketDao.deleteAllMarketData()
    }

    override suspend fun getMarketList(): List<MarketModel> {
        return marketDao.fetchMarketData()
    }
}