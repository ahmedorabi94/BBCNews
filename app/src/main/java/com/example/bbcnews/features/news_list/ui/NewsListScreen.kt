package com.example.bbcnews.features.news_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bbcnews.BuildConfig
import com.example.bbcnews.core.data.Resource
import com.example.bbcnews.core.domain.models.Article
import com.example.bbcnews.features.news_list.viewmodel.GetNewsListViewModel
import java.util.Locale

@Preview
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    viewModel: GetNewsListViewModel = hiltViewModel(),
    onItemClick: ((Article) -> Unit)? = null
) {
    val newsItems by viewModel.newsResponse.collectAsStateWithLifecycle()

    when(newsItems.status){
        Resource.Status.SUCCESS -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = BuildConfig.FLAVOR.uppercase(Locale.ROOT) + " News Headlines",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                )

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    newsItems.data?.let {
                        items(it.size) { index ->
                            NewsCard(
                                title = it[index].title ?: "",
                                date = it[index].publishedAt ?: "",
                                imageUrl = it[index].urlToImage ?: "",
                                onClick = {
                                    onItemClick?.invoke(it[index])
                                }
                            )
                        }
                    }

                }

//                // Loading Indicator
//                if (isLoading) {
//                    CircularProgressIndicator(
//                        modifier = Modifier.size(60.dp),
//                        color = Color.Black
//                    )
//                }
            }
        }
        Resource.Status.ERROR -> {

        }
        Resource.Status.LOADING -> {
            CircularProgressIndicator(
                modifier = modifier.size(60.dp),
                color = Color.Black
            )
        }
    }


}
