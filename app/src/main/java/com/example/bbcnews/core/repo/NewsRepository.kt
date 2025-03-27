package com.example.bbcnews.core.repo

import javax.inject.Inject

class NewsRepository @Inject constructor(private val dataSource: NewsDataSource) {

    suspend fun getNewsResponse() = dataSource.getNewsResponse()

}