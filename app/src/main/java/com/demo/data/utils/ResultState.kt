package com.demo.data.utils

import com.demo.data.db.entities.MarketModel

sealed class ResultState {
    data class Success(val data: List<MarketModel>) : ResultState()
    data class Error(val errorMessage: String?) : ResultState()
}
