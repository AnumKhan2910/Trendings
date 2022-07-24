package com.example.gittrendings.data

import retrofit2.http.GET

interface TrendingService {

    @GET("/search/repositories?q=language=+sort:stars")
    suspend fun fetchTrendingRepo() : TrendingResponse
}