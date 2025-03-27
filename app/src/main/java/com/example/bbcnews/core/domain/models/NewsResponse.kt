package com.example.bbcnews.core.domain.models

data class NewsResponse(
    val articles: List<Article> = emptyList(),
    val status: String = "",
    val totalResults: Int = 0
)