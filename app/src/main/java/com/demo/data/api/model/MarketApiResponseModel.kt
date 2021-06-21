package com.demo.data.api.model


import com.google.gson.annotations.SerializedName

data class MarketApiResponseModel(
    @SerializedName("markets")
    val markets: List<Market>,
    @SerializedName("pagination")
    val pagination: Pagination
)