package com.example.mynewsappcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsUi(
    val title: String,
    val author: String,
    val description: String,
    val urlToImage: String
)
