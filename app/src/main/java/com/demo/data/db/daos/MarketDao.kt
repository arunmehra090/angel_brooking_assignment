package com.demo.data.db.daos

import androidx.room.*
import com.demo.data.db.entities.MarketModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarketData(list: List<MarketModel>)

    @Query("DELETE from Markets")
    suspend fun deleteAllMarketData()

    @Query("SELECT * from Markets")
    suspend fun fetchMarketData(): List<MarketModel>
}