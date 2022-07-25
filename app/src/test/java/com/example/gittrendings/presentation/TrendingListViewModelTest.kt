package com.example.gittrendings.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gittrendings.domain.TrendingDataUseCase
import com.example.gittrendings.network.TrendingUIState
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingListViewModelTest {

    @Mock
    private lateinit var trendingDataUseCase: TrendingDataUseCase

    private lateinit var trendingListViewModel: TrendingListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        trendingListViewModel = TrendingListViewModel(
            trendingDataUseCase
        )
    }

    @After
    fun tearDownDispatchers() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given Failure response, when getTrendingRepo is called UI state should be produced`() {
        val errorMessage = ""
        whenever(trendingDataUseCase(true)).thenReturn(
            flow {
                emit(TrendingUIState.Failure(message = errorMessage))
            }
        )

        trendingListViewModel.fetchTrendingProjects(true)

        val viewState = trendingListViewModel.viewStateLiveData.value

        Assert.assertNotNull(viewState is TrendingUIState.Failure)
    }

    @Test
    fun `Given valid response, when getTrendingRepo is called UI state should be produced`() {

        whenever(trendingDataUseCase(true)).thenReturn(
            flow {
                emit(TrendingUIState.Success(listOf()))
            }
        )

        trendingListViewModel.fetchTrendingProjects(true)

        val viewState = trendingListViewModel.viewStateLiveData.value

        Assert.assertNotNull(viewState is TrendingUIState.Success)
    }

}