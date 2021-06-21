package com.demo.presentation.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.data.repository.MarketRepositoryImpl

class MarketViewModelFactory(
    private val app: Application,
    private val marketRepositoryImpl: MarketRepositoryImpl
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarketListViewModel(app, marketRepositoryImpl) as T
    }
}