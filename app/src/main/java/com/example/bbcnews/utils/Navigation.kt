package com.example.bbcnews.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.features.news_details.NewsDetailsScreen
import com.example.bbcnews.features.news_list.ui.NewsListScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


enum class Screen {
    NewsListScreen,
    NewsDetailsScreen
}
sealed class NavigationItem(val route: String) {
    object NewsListScreen : NavigationItem(Screen.NewsListScreen.name)
    object NewsDetailsScreen : NavigationItem(Screen.NewsDetailsScreen.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.NewsListScreen.route,

    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.NewsListScreen.route) {
            NewsListScreen(modifier = modifier , onItemClick = {
                val json = Gson().toJson(it)
                 var encode = URLEncoder.encode(json,StandardCharsets.UTF_8.toString())

                navController.navigate(NavigationItem.NewsDetailsScreen.route +  "/$encode")
            })
        }

        composable(NavigationItem.NewsDetailsScreen.route + "/{article}") { backStackEntry ->
            val json = backStackEntry.arguments?.getString("article")
            val decodedJson = URLDecoder.decode(json, StandardCharsets.UTF_8.toString())
            val article = Gson().fromJson(decodedJson, Article::class.java)
            NewsDetailsScreen(
                title = article?.title ?: "",
                imageUrl = article?.urlToImage ?: "",
                description = article?.description ?: "",
                publishedAt = article?.publishedAt ?: "",
                content = article?.content ?: "",
                author = article?.author ?: "",
            )
        }
    }
}