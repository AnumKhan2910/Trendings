package com.example.gittrendings.presentation

import androidx.lifecycle.*
import com.example.gittrendings.data.TrendingUIData
import com.example.gittrendings.domain.TrendingDataUseCase
import com.example.gittrendings.network.TrendingUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingListViewModel @Inject constructor(
    private val trendingDataUseCase: TrendingDataUseCase
): ViewModel() {

    private val _trendingList = MutableLiveData<List<TrendingUIData>>()
    val trendingList: LiveData<List<TrendingUIData>>
        get() = _trendingList

    val viewStateLiveData: LiveData<TrendingUIState>
        get() = _viewStateLiveData
    private val _viewStateLiveData: MutableLiveData<TrendingUIState> by lazy {
        MutableLiveData()
    }

    fun fetchTrendingProjects() {
        viewModelScope.launch {
            trendingDataUseCase(Unit).collectLatest {
                _viewStateLiveData.value = it
                if (it is TrendingUIState.Success) {
                    _trendingList.value = it.data
                }
            }
        }
    }
 }