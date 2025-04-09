package com.example.mynewsappcompose.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.mynewsappcompose.domain.NewsUi
import com.example.mynewsappcompose.presentation.viewmodel.NewsViewModel
import com.example.mynewsappcompose.presentation.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewmodel: NewsViewModel = hiltViewModel<NewsViewModel>()
) {
    val newsUiState by viewmodel.newsUiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("US Top Headlines") }) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (newsUiState) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Success -> NewsList((newsUiState as UiState.Success).data)
                is UiState.Error -> Text(
                    "News Not Found",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }

}

@Composable
fun NewsList(data: List<NewsUi>) {
    LazyColumn {
        items(data) {
            NewsItem(it)

        }
    }
}

@Composable
fun NewsItem(news: NewsUi) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.height(150.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) { // ← Make the Row as tall as the tallest child

            AsyncImage(
                model = news.urlToImage,
                contentDescription = "News Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentHeight()
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(8.dp))

            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = news.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = news.description,
                    modifier = Modifier
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }

}
