package com.example.bbcnews.features.news_list.di

import com.example.bbcnews.core.data.ApiService
import com.example.bbcnews.core.repo.NewsDataSource
import com.example.bbcnews.features.news_list.framework.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsListModule {


    @Singleton
    @Provides
    fun provideInApiNewsListDataSource(apiService: ApiService): NewsDataSource {
        return NewsRepositoryImpl(apiService)
    }


}