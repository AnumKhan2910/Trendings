package com.example.gittrendings.domain

import com.example.gittrendings.R
import com.example.gittrendings.data.*
import com.example.gittrendings.network.ResponseResult
import com.example.gittrendings.network.TrendingUIState
import com.example.gittrendings.utils.FlowUseCase
import com.example.gittrendings.utils.StringResourceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TrendingDataUseCase : FlowUseCase<Unit, TrendingUIState>

class DefaultTrendingDataUseCase @Inject constructor(
    private val stringResourceManager: StringResourceManager,
    private val repository: TrendingRepository
) : TrendingDataUseCase {

    override fun invoke(request: Unit): Flow<TrendingUIState> = flow {
        emit(TrendingUIState.Loading)
        emit(
            when (val response = repository.getTrendingRepo()) {
                is ResponseResult.Success -> onSuccess(response)
                is ResponseResult.Failure -> onFailure(response)
                is ResponseResult.NetworkError -> onNetworkError()
            }
        )
    }

    private fun onSuccess(result: ResponseResult.Success<TrendingResponse>) =
        TrendingUIState.Success(result.data.items.map { TrendingUIData(
            id = it.id,
            name = it.name.orEmpty(),
            fullName = it.owner.login,
            description = it.description.orEmpty(),
            stargazersCount = it.stargazersCount.toString(),
            language = it.language.orEmpty(),
            image = it.owner.avatarUrl)
        })

    private fun onNetworkError(): TrendingUIState.Failure =
        TrendingUIState.Failure(stringResourceManager.getString(R.string.unexpected_error))


    private fun onFailure(
        result: ResponseResult.Failure
    ): TrendingUIState.Failure =
        TrendingUIState.Failure(
            result.error.message ?: stringResourceManager.getString(R.string.unexpected_error)
        )

}