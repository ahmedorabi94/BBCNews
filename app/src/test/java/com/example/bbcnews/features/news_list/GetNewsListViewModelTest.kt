package com.example.bbcnews.features.news_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bbcnews.TestCoroutineRule
import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.core.domain.models.Source
import com.example.bbcnews.core.domain.usecases.GetNewsUseCase
import com.example.bbcnews.features.news_list.viewmodel.GetNewsListViewModel

import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetNewsListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: GetNewsListViewModel


    @Mock
    private lateinit var useCase: GetNewsUseCase


    @Before
    fun setup() {
        // viewModel = GetNewsListViewModel(useCase)
    }

    @Test
    fun shouldGetNewsListSuccessResponse() {

        val article = Article(
            "BBC News", "", "",
            "", Source("", ""), "Police arrest", "", ""
        )
        val articleList = arrayListOf(
            article
        )


        val resource = Resource.success(articleList)

        val flow = flow {
            emit(resource)
        }


        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke()


            viewModel = GetNewsListViewModel(useCase)

            Mockito.verify(useCase).invoke()

            val emittedValue = viewModel.newsResponse.first()

            Assert.assertEquals(emittedValue, resource)
            Assert.assertEquals(emittedValue.data?.get(0)?.author, "BBC News")


        }
    }
}