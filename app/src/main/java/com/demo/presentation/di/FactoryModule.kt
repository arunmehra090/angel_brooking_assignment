package com.demo.presentation.di

import android.app.Application
import com.demo.data.repository.MarketRepositoryImpl
import com.demo.presentation.ui.viewmodels.MarketViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        marketRepositoryImpl: MarketRepositoryImpl
    ): MarketViewModelFactory {
        return MarketViewModelFactory(
            application,
            marketRepositoryImpl
        )
    }
}








