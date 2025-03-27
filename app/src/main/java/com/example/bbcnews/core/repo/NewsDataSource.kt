package com.example.bbcnews.core.repo

import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.domain.models.Article
import kotlinx.coroutines.flow.Flow


interface NewsDataSource {

    suspend fun getNewsResponse(): Flow<Resource<List<Article>>>

}