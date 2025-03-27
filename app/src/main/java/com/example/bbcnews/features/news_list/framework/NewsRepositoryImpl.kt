package com.example.bbcnews.features.news_list.framework

import com.example.bbcnews.core.data.ApiService
import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.data.ResultWrapper
import com.example.bbcnews.core.data.safeApiCall
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.core.repo.NewsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NewsDataSource {


    override suspend fun getNewsResponse(): Flow<Resource<List<Article>>> {
        return flow {

            emit(Resource.Companion.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getNewsResponseAsync()
            }


            when (response) {
                is ResultWrapper.Success -> {
                    val sortedList = response.value.articles.sortedBy { article ->
                        article.publishedAt
                    }
                    emit(Resource.Companion.success(sortedList))
                }

                is ResultWrapper.Error -> {
                    emit(Resource.Companion.error(response.error?.message ?: "Unknown Error"))

                }

                is ResultWrapper.NetworkError -> {
                    emit(Resource.Companion.error("NetworkError"))

                }

                ResultWrapper.NoContentError -> {
                    emit(Resource.Companion.error("NoContentError"))

                }
            }

        }.flowOn(Dispatchers.IO)
    }


}