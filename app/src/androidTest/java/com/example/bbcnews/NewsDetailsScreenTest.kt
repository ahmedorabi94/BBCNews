package com.example.bbcnews

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.core.domain.models.Source
import com.example.bbcnews.ui.news_details.NewsDetailsScreen
import com.example.bbcnews.ui.theme.BBCNewsTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)

class NewsDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDetailsScreen() {

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

        composeTestRule.setContent {
            BBCNewsTheme {
                NewsDetailsScreen(
                    title = article.title ?: "",
                    imageUrl = article.urlToImage ?: "",
                    description = article.description ?: "",
                    publishedAt = article.publishedAt ?: "",
                    content = article.content ?: "",
                    author = article.author ?: "",
                )
            }
        }

        composeTestRule.onNodeWithText(article.title.toString()).assertExists()
        composeTestRule.onNodeWithText(article.description.toString()).assertExists()
        composeTestRule.onNodeWithText(article.publishedAt.toString()).assertExists()
        composeTestRule.onNodeWithText(article.content.toString()).assertExists()
        composeTestRule.onNodeWithText(article.author.toString()).assertExists()


        composeTestRule.onNodeWithText(article.title.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.description.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.publishedAt.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.content.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.author.toString()).assertIsDisplayed()

    }

}