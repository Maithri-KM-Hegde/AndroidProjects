package com.example.mynewsappcompose.data.dto

sealed class BaseResponse<out T> {
    data class Success<T>(val data: T) : BaseResponse<T>()
    data class Error(val errorMessage: String) : BaseResponse<Nothing>()
}
