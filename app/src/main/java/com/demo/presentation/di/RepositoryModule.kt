package com.demo.presentation.di

import com.demo.data.repository.MarketRepositoryImpl
import com.demo.domain.usecases.datasource.MarketLocalDataSource
import com.demo.domain.usecases.datasource.MarketRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMarketRepository(
        marketLocalDataSource: MarketLocalDataSource,
        marketRemoteDataSource: MarketRemoteDataSource
    ): MarketRepositoryImpl {
        return MarketRepositoryImpl(
            marketLocalDataSource,
            marketRemoteDataSource
        )
    }

}














