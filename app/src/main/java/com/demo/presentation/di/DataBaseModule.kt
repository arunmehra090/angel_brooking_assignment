package com.demo.presentation.di

import android.app.Application
import androidx.room.Room
import com.demo.data.db.AppDatabase
import com.demo.data.db.daos.MarketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideMarketDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java,
            AppDatabase.MARKET_DB)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(appDatabase: AppDatabase): MarketDao {
        return appDatabase.marketDao()
    }
}