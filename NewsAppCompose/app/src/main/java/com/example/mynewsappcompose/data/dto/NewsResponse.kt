package com.example.mynewsappcompose.data.dto

import com.example.mynewsappcompose.data.dto.Article

data class NewsResponse(
    val articles: List<Article>?,
    val status: String? = "",
    val totalResults: Int? = 0
)