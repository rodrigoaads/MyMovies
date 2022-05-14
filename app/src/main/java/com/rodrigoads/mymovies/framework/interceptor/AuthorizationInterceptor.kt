package com.rodrigoads.mymovies.framework.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val apiKey: String,
    private val apiToken: String,
    private val responseLanguage: String,
    private val responseRegion: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val newUrl = url.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .addQueryParameter(LANGUAGE, responseLanguage)
            .addQueryParameter(REGION, responseRegion)
            .build()

        return chain.proceed(
            request.newBuilder()
                .header(API_TOKEN, apiToken)
                .url(newUrl)
                .build()
        )
    }

    companion object {
        const val API_KEY = "api_key"
        const val API_TOKEN = "Bearer"
        const val LANGUAGE = "language"
        const val REGION = "region"
    }
}