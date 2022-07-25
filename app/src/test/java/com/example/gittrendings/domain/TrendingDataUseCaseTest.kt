package com.example.gittrendings.domain

import com.example.gittrendings.R
import com.example.gittrendings.data.*
import com.example.gittrendings.network.*
import com.example.gittrendings.utils.StringResourceManager
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingDataUseCaseTest {

    @Mock
    private lateinit var stringResourceManager: StringResourceManager

    @Mock
    private lateinit var repository: TrendingRepository

    private lateinit var trendingDataUseCase: TrendingDataUseCase

    @Before
    fun setup() {
        trendingDataUseCase = DefaultTrendingDataUseCase(
            stringResourceManager,
            repository
        )
    }

    @Test
    fun `execute get trending projects and expect network failure`() = runBlocking {
        val responseResult = ResponseResult.NetworkError
        val message = "not able to reach network"
        val errorViewState = TrendingUIState.Failure(message)
        val expectedViewStates = arrayListOf<TrendingUIState>().apply {
            add(TrendingUIState.Loading)
            add(errorViewState)
        }.toTypedArray()

        whenever(repository.getTrendingRepo(true)).thenReturn(responseResult)
        whenever(stringResourceManager.getString(R.string.unexpected_error)).thenReturn(message)

        val actualViewStates = trendingDataUseCase(true).take(2).toList().toTypedArray()

        Assert.assertNotNull(actualViewStates)
        Assert.assertArrayEquals(expectedViewStates, actualViewStates)
    }

    @Test
    fun `execute get trending projects and expect api failure`() = runBlocking {
        val message = "validation failed"
        val errorResponse = ErrorResponse(
            statusCode = 402,
            message = message
        )
        val responseResult = ResponseResult.Failure(errorResponse)
        val errorViewState = TrendingUIState.Failure(message)
        val expectedViewStates = arrayListOf<TrendingUIState>().apply {
            add(TrendingUIState.Loading)
            add(errorViewState)
        }.toTypedArray()

        whenever(repository.getTrendingRepo(true)).thenReturn(responseResult)

        val actualViewStates = trendingDataUseCase(true).take(2).toList().toTypedArray()

        Assert.assertNotNull(actualViewStates)
        Assert.assertArrayEquals(expectedViewStates, actualViewStates)
    }

    @Test
    fun `execute get trending projects and expect success response`() = runBlocking {
        val data = TrendingResponse(listOf())
        val responseResult = ResponseResult.Success(data)
        val successUIData = TrendingUIState.Success(listOf())
        val expectedViewStates = arrayListOf<TrendingUIState>().apply {
            add(TrendingUIState.Loading)
            add(successUIData)
        }.toTypedArray()

        whenever(repository.getTrendingRepo(true)).thenReturn(responseResult)

        val actualViewStates = trendingDataUseCase(true).take(2).toList().toTypedArray()

        Assert.assertNotNull(actualViewStates)
        Assert.assertArrayEquals(expectedViewStates, actualViewStates)
    }
}