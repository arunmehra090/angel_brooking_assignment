package com.demo.presentation.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.demo.data.repository.MarketRepositoryImpl
import com.demo.data.utils.Constants
import com.demo.data.utils.NetworkUtils
import com.demo.data.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MarketListViewModel(
    private val appContext: Application,
    private val marketRepositoryImpl: MarketRepositoryImpl
) : AndroidViewModel(appContext) {

    private val marketListLiveData : MutableLiveData<ResultState> = MutableLiveData()

    fun getMarketListLiveData(): LiveData<ResultState> = marketListLiveData

    fun fetchMarketListFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        if (NetworkUtils.isNetworkAvailable(appContext)) {
            try {
                if (marketRepositoryImpl.fetchMarketList()) {
                    marketListLiveData.postValue(ResultState.Success(marketRepositoryImpl.getMarketList()))
                } else {
                    marketListLiveData.postValue(ResultState.Error("some error occurred"))
                }
            } catch (e: Exception) {
                marketListLiveData.postValue(ResultState.Error(e.message ?: "some error occurred"))
                cancel(e.message?: "Some error occurred")
            }
        } else {
            marketListLiveData.postValue(ResultState.Error(Constants.NO_INTERNET_CONNECTION))
        }
    }
}