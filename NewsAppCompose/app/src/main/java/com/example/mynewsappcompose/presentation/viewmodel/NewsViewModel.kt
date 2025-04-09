package com.example.mynewsappcompose.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsappcompose.data.dto.BaseQuery
import com.example.mynewsappcompose.data.dto.BaseResponse
import com.example.mynewsappcompose.domain.FetchTopHeadlineUseCase
import com.example.mynewsappcompose.domain.NewsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsHeadLinesUseCase: FetchTopHeadlineUseCase) :
    ViewModel() {
    companion object {
        private const val TAG = "NewsViewModel"
    }

    private var _newsState = MutableStateFlow<UiState<List<NewsUi>>>(UiState.Loading)
    val newsUiState get() = _newsState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            getNewsHeadLinesUseCase(createQueryParams()).onStart {
                Log.d(TAG, "fetchTopHeadLines: Init ")
            }.catch {
                Log.e(TAG, "fetchTopHeadLines: Exception")
            }.collect {
                when (it) {
                    is BaseResponse.Success -> {
                        _newsState.value = UiState.Success(it.data)
                    }

                    is BaseResponse.Error -> {
                        _newsState.value = UiState.Error(it.errorMessage)
                    }
                }
            }

        }
    }

    private fun createQueryParams(): Map<String, String> =
        BaseQuery(country = "us").toQueryMap()
}