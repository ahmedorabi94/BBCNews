package com.example.bbcnews.core.domain.usecases


import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.core.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {


    suspend operator fun invoke(): Flow<Resource<List<Article>>> {
        return newsRepository.getNewsResponse()
    }

}