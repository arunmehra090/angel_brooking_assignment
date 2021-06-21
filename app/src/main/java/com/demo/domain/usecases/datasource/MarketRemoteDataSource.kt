package com.demo.domain.usecases.datasource

import com.demo.data.api.model.MarketApiResponseModel
import retrofit2.Response

interface MarketRemoteDataSource {
    suspend fun getMarketList(): Response<MarketApiResponseModel>
}