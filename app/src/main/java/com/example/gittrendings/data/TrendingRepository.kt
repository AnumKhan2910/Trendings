package com.example.gittrendings.domain

import com.example.gittrendings.data.*
import com.example.gittrendings.network.ResponseResult
import com.example.gittrendings.network.callApi
import javax.inject.Inject

interface TrendingRepository {
    suspend fun getTrendingRepo() : ResponseResult<TrendingResponse>
}

class DefaultTrendingRepository @Inject constructor(
    private val service: TrendingService
): TrendingRepository {

    override suspend fun getTrendingRepo(): ResponseResult<TrendingResponse> =
        callApi { service.fetchTrendingRepo() }

}