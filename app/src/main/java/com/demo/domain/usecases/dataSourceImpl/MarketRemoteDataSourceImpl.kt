package com.demo.domain.usecases.dataSourceImpl

import com.demo.data.api.ApiService
import com.demo.data.api.model.MarketApiResponseModel
import com.demo.domain.usecases.datasource.MarketRemoteDataSource
import retrofit2.Response

class MarketRemoteDataSourceImpl(private val apiService: ApiService): MarketRemoteDataSource {
    override suspend fun getMarketList(): Response<MarketApiResponseModel> {
        return apiService.getMarketsResponse()
    }
}