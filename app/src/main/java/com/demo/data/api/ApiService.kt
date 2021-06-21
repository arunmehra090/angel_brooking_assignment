package com.demo.data.api

import com.demo.data.api.model.MarketApiResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/markets")
    suspend fun getMarketsResponse(): Response<MarketApiResponseModel>
}