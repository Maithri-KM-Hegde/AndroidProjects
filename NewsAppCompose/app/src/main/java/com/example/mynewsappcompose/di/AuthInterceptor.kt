package com.example.mynewsappcompose.di

import com.example.mynewsappcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * API_KEY is configured at local.properties.
 * Follow README.md to generate API Key
 */
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = BuildConfig.API_KEY
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey)
            .build()
        return chain.proceed(request)
    }
}