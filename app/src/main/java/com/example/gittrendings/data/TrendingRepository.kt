package com.example.gittrendings.domain

import com.example.gittrendings.data.TrendingData
import com.example.gittrendings.data.TrendingService
import com.example.gittrendings.network.ResponseResult
import com.example.gittrendings.network.callApi
import javax.inject.Inject

interface TrendingRepository {
    suspend fun getTrendingRepo() : ResponseResult<List<TrendingData>>
}

class DefaultTrendingRepository @Inject constructor(
    private val service: TrendingService
): TrendingRepository {

    override suspend fun getTrendingRepo(): ResponseResult<List<TrendingData>> =
        callApi { service.fetchTrendingRepo() }

}