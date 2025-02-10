package com.example.newsapplication.domain

import com.example.newsapplication.data.dto.Article
import com.example.newsapplication.data.dto.BaseResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchTopHeadlines(queryParams: Map<String, String>): Flow<BaseResponse<List<Article>>>
}