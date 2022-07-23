package com.example.gittrendings.network

import com.example.gittrendings.data.TrendingData

sealed class TrendingUIState {
    data class Success(val data: List<TrendingData>): TrendingUIState()
    data class Failure(val message: String): TrendingUIState()
    object Loading: TrendingUIState()
}