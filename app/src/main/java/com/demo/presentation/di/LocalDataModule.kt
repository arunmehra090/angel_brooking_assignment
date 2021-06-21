package com.demo.presentation.di

import com.demo.data.db.daos.MarketDao
import com.demo.domain.usecases.dataSourceImpl.MarketLocalDataSourceImpl
import com.demo.domain.usecases.datasource.MarketLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(marketDao: MarketDao): MarketLocalDataSource {
        return MarketLocalDataSourceImpl(marketDao)
    }
}













