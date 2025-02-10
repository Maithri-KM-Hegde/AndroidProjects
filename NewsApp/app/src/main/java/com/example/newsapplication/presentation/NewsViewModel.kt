package com.example.newsapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.dto.BaseQuery
import com.example.newsapplication.data.dto.BaseResponse
import com.example.newsapplication.domain.FetchTopHeadlineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsUseCase: FetchTopHeadlineUseCase) :
    ViewModel() {
    companion object {
        private const val TAG = "NewsViewModel"
    }

    private val newsHeadlineState = MutableStateFlow<NewsUiState>(NewsUiState.Init)
    val newsUiState: StateFlow<NewsUiState> get() = newsHeadlineState

    fun fetchTopHeadLines() {
        viewModelScope.launch {
            newsUseCase(createQueryParams()).onStart {
                Log.d(TAG, "fetchTopHeadLines: Init ")
            }.catch {
                Log.e(TAG, "fetchTopHeadLines: Exception")
            }.collect {
                when (it) {
                    is BaseResponse.Success -> {
                        newsHeadlineState.value = NewsUiState.SuccessState(it.data)
                    }

                    is BaseResponse.Error -> {
                        newsHeadlineState.value = NewsUiState.ErrorState(it.errorMessage)
                    }
                }
            }
        }
    }

    private fun createQueryParams(): Map<String, String> =
        BaseQuery(country = "us").toQueryMap()

}