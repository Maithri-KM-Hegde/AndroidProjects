package com.example.mynewsappcompose.domain

import com.example.mynewsappcompose.data.dto.Article
import com.example.mynewsappcompose.data.dto.BaseResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchTopHeadlines(queryParams: Map<String, String>): Flow<BaseResponse<List<Article>>>
}