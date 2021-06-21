package com.demo.presentation.di

import com.demo.data.api.ApiService
import com.demo.domain.usecases.dataSourceImpl.MarketRemoteDataSourceImpl
import com.demo.domain.usecases.datasource.MarketRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideMarketRemoteDataSource(
        apiService: ApiService
    ):MarketRemoteDataSource{
       return MarketRemoteDataSourceImpl(apiService)
    }

}












