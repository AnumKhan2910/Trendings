package com.example.gittrendings.data

import com.example.gittrendings.data.TrendingData
import retrofit2.http.GET

interface TrendingService {

    @GET("/search/repositories?q=language=+sort:stars")
    suspend fun fetchTrendingRepo() : List<TrendingData>
}