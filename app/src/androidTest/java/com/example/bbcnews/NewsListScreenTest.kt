package com.example.bbcnews

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.core.domain.models.Source
import com.example.bbcnews.core.domain.usecases.GetNewsUseCase
import com.example.bbcnews.features.news_list.viewmodel.GetNewsListViewModel
import com.example.bbcnews.features.news_list.ui.NewsListScreen
import com.example.bbcnews.theme.BBCNewsTheme
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsListScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    // Mock dependencies
    private lateinit var useCase: GetNewsUseCase
    private lateinit var viewModel: GetNewsListViewModel

    val article = Article(
        "BBC News",
        "A Canadian man has been arrested in British Columbia for opening a mobile shop to sell cocaine",
        "Jerry Martin of Vancouver has said he wanted to sell drugs without fentanyl to help prevent harms.",
        "2023-05-05T17:07:15.8602783Z",
        Source("", ""),
        "Police arrest man for opening store selling hard drugs in Canada",
        "http://www.bbc.co.uk/news/world-us-canada-65487199",
        "https://ichef.bbci.co.uk/news/1024/branded_news/12C6E/production/_129601967_gettyimages-1240791902.jpg"
    )


    @Before
    fun setup() {
        useCase = mockk()

        val articleList = arrayListOf(
            article
        )


        val resource = Resource.success(articleList)

        val flow = flow {
            emit(resource)
        }


        // Set up mock response
        coEvery { useCase.invoke() } returns flow

        viewModel = GetNewsListViewModel(useCase)

    }

    @Test
    fun testNewsListScreen() {
        // Set up compose with viewmodel
        composeTestRule.setContent {
            BBCNewsTheme {
                NewsListScreen(viewModel = viewModel)
            }
        }


        // Wait for loading to complete
        composeTestRule.waitForIdle()

        // Verify items are displayed
        composeTestRule.onNodeWithText(article.title.toString()).assertExists()

    }

}