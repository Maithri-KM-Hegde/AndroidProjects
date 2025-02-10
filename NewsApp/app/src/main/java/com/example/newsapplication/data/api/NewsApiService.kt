package com.example.newsapplication.data.api

import com.example.newsapplication.data.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @QueryMap queryParams: Map<String, String>
    ): Response<NewsResponse>
}