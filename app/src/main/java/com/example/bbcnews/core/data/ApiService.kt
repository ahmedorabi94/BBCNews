package com.example.bbcnews.core.data

import com.example.bbcnews.core.domain.models.NewsResponse
import com.example.bbcnews.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines?apiKey=499c1384b1f1467c90f8276feeb2a2ba")
    suspend fun getNewsResponseAsync(@Query("sources") sources: String = BuildConfig.sources): NewsResponse


}