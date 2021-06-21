package com.demo.data.repository

import android.util.Log
import com.demo.data.api.model.Market
import com.demo.data.api.model.MarketApiResponseModel
import com.demo.data.db.entities.MarketModel
import com.demo.domain.usecases.datasource.MarketLocalDataSource
import com.demo.domain.usecases.datasource.MarketRemoteDataSource

class MarketRepositoryImpl(
    private val marketLocalDataSource: MarketLocalDataSource,
    private val marketRemoteDataSource: MarketRemoteDataSource
) {
    suspend fun fetchMarketList(): Boolean {
        marketRemoteDataSource.getMarketList().apply {
            try {
                if (isSuccessful && code() == 200 && body() is MarketApiResponseModel) {
                    (body() as MarketApiResponseModel).apply {
                        if (markets.isNotEmpty()) {
                            deleteAndSaveDataIntoDb(markets)
                            return true
                        }
                    }
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("error", it) }
            }
        }
        return false
    }

    private suspend fun deleteAndSaveDataIntoDb(marketList: List<Market>) {
        marketLocalDataSource.deleteMarketListFromDB()
        marketLocalDataSource.saveMarketListToDB(ArrayList<MarketModel>().apply {
            marketList.forEach {
                add(
                    MarketModel(
                        it.change24h,
                        it.exchangeId,
                        it.price,
                        it.priceUnconverted,
                        it.spread,
                        it.status,
                        it.symbol,
                        it.time,
                        it.volume24h
                    )
                )
            }
        })
    }

    suspend fun getMarketList(): List<MarketModel> {
        return marketLocalDataSource.getMarketList()
    }
}