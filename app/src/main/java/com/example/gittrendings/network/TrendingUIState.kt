package com.example.gittrendings.network

import com.example.gittrendings.data.TrendingUIData

sealed class TrendingUIState {
    data class Success(val data: List<TrendingUIData>): TrendingUIState()
    data class Failure(val message: String): TrendingUIState()
    object Loading: TrendingUIState()
}