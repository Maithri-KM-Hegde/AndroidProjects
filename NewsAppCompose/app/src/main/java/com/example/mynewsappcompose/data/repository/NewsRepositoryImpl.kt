package com.example.mynewsappcompose.data.repository

import com.example.mynewsappcompose.data.api.NewsApiService
import com.example.mynewsappcompose.data.dto.Article
import com.example.mynewsappcompose.data.dto.BaseResponse
import com.example.mynewsappcompose.domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: NewsApiService) :
    NewsRepository {

    override suspend fun fetchTopHeadlines(queryParams: Map<String, String>): Flow<BaseResponse<List<Article>>> =
        flow {
            val response = apiService.getTopHeadlines(queryParams)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    if (it.status == "ok") {
                        emit(BaseResponse.Success(body.articles ?: emptyList()))
                    } else {
                        emit(BaseResponse.Error("Unexpected response format"))
                    }
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = errorBody ?: "Unknown error occurred"
                emit(BaseResponse.Error(errorMessage))
            }
        }.catch { e ->
            emit(BaseResponse.Error("Exception occurred: ${e.message}"))
        }
}