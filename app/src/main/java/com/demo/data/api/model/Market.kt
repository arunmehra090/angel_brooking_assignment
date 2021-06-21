package com.demo.data.api.model


import com.google.gson.annotations.SerializedName

data class Market(
    @SerializedName("change_24h")
    val change24h: Double,
    @SerializedName("exchange_id")
    val exchangeId: String = "",
    @SerializedName("price")
    val price: Double,
    @SerializedName("price_unconverted")
    val priceUnconverted: Double,
    @SerializedName("spread")
    val spread: Double,
    @SerializedName("status")
    val status: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("time")
    val time: String = "",
    @SerializedName("volume_24h")
    val volume24h: Double
)