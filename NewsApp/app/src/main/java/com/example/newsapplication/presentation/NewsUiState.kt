package com.example.newsapplication.presentation

import com.example.newsapplication.domain.NewsHeadline

sealed class NewsUiState {
    data object Init : NewsUiState()
    data class SuccessState(val newsList: List<NewsHeadline>) : NewsUiState()
    data class ErrorState(val message: String) : NewsUiState()
}