package com.example.bbcnews.ui.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.core.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetNewsListViewModel @Inject constructor(
    private val useCase: GetNewsUseCase
) :
    ViewModel() {

    private val _newsResponse = MutableStateFlow(
        Resource<List<Article>>(
            status = Resource.Status.LOADING,
            data = null,
            message = ""
        )
    )
    val newsResponse: StateFlow<Resource<List<Article>>> = _newsResponse

    init {
        viewModelScope.launch {
            useCase.invoke()
                .collect { response ->
                    _newsResponse.value = response
                }
        }
    }


}