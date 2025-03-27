package com.example.bbcnews.ui.news_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Preview
@Composable
fun NewsDetailsScreen(
    title: String = "",
    imageUrl: String= "",
    description: String= "",
    publishedAt: String= "",
    content: String= "",
    author: String= "",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Title
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, start = 8.dp, end = 8.dp)
        )

        // Image
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            contentScale = ContentScale.Fit
        )

        // Description Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Description",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.wrapContentWidth()
            )
            Text(
                text = description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f)
            )
        }

        // Published At Section
        Row(
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
        ) {
            Text(
                text = "Published at",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = publishedAt,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp))
        }

        // Content Section
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "Content",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = content,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }

        // Author Section
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .padding(bottom = 30.dp)
        ) {
            Text(
                text = "Author",
                fontSize = 15.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = author,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}