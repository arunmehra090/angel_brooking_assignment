package com.demo.data.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Markets")
data class MarketModel(
    @SerializedName("change_24h")
    val change24h: Double,
    @SerializedName("exchange_id")
    val exchangeId: String? = "",
    @SerializedName("price")
    val price: Double,
    @SerializedName("price_unconverted")
    val priceUnconverted: Double,
    @SerializedName("spread")
    val spread: Double,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("symbol")
    val symbol: String? = "",
    @SerializedName("time")
    val time: String? = "",
    @SerializedName("volume_24h")
    val volume24h: Double
): Parcelable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var userId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
        userId = parcel.readInt()
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeDouble(change24h)
            writeString(exchangeId)
            writeDouble(price)
            writeDouble(priceUnconverted)
            writeDouble(spread)
            writeString(status)
            writeString(symbol)
            writeString(time)
            writeDouble(volume24h)
        }
    }

    companion object CREATOR : Parcelable.Creator<MarketModel> {
        override fun createFromParcel(parcel: Parcel): MarketModel {
            return MarketModel(parcel)
        }

        override fun newArray(size: Int): Array<MarketModel?> {
            return arrayOfNulls(size)
        }
    }
}