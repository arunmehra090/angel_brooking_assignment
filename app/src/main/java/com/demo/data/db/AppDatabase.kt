package com.demo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.data.db.daos.MarketDao
import com.demo.data.db.entities.MarketModel

@Database(version = 1, entities = [MarketModel::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val MARKET_DB = "market.db"
    }
    abstract fun marketDao(): MarketDao
}
