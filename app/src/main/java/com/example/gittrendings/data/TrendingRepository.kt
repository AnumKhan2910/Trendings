package com.example.gittrendings.data

import com.example.gittrendings.data.db.TrendingDao
import com.example.gittrendings.network.*
import javax.inject.Inject

interface TrendingRepository {
    suspend fun getTrendingRepo(refresh: Boolean) : ResponseResult<TrendingResponse>
}

class DefaultTrendingRepository @Inject constructor(
    private val service: TrendingService,
    private val trendingDao: TrendingDao
): TrendingRepository {

    override suspend fun getTrendingRepo(refresh: Boolean): ResponseResult<TrendingResponse> {

            val data = trendingDao.getAllData()

            return if (refresh || data.isEmpty()) {
                trendingDao.deleteData()
                callApi {
                    service.fetchTrendingRepo()
                }
            } else {
                ResponseResult.Success(TrendingResponse(data))
            }

    }

    private suspend fun <T> callApi(apiCall: suspend () -> T): ResponseResult<T> {
        return try {
            val result = apiCall.invoke()
            trendingDao.insert((result as TrendingResponse).items)
            ResponseResult.Success(result)
        } catch (throwable: Throwable) {
            ResponseResult.Failure(createUnexpectedError(throwable))
        }
    }

    private fun createUnexpectedError(throwable: Throwable) =
        ErrorResponse(-1, -1, throwable.message)

}