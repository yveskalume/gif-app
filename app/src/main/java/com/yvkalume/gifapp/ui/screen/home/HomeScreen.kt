package com.yvkalume.gifapp.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.yvkalume.gifapp.R
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.ui.components.EmptyView
import com.yvkalume.gifapp.ui.components.GifListView
import com.yvkalume.gifapp.ui.components.LoadingView
import com.yvkalume.gifapp.ui.screen.home.logic.HomeUiState
import com.yvkalume.gifapp.ui.screen.home.logic.HomeViewModel
import com.yvkalume.gifapp.ui.theme.GifAppTheme

@Composable
fun HomeRoute(homeViewModel: HomeViewModel = mavericksViewModel()) {
    val gifsState by homeViewModel.collectAsState(HomeUiState::gifs)
    HomeScreen(
        gifsState = gifsState,
        onFavoriteClick = { homeViewModel.toggleFavorite(it) }
    )
}

@Composable
fun HomeScreen(
    gifsState: Async<List<Gif>>,
    onFavoriteClick: (Gif) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding), contentAlignment = Alignment.Center) {
            when (gifsState) {
                is Fail -> {
                    EmptyView()
                }

                is Loading -> {
                    LoadingView()
                }

                is Success -> {
                    val gifs = gifsState.invoke()
                    if (gifs.isNotEmpty()) {
                        GifListView(
                            gifs = gifs,
                            onFavoriteClick = onFavoriteClick
                        )
                    } else {
                        EmptyView()
                    }
                }

                Uninitialized -> {}
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    GifAppTheme {
        HomeScreen(
            gifsState = Success(emptyList()),
            onFavoriteClick = {}
        )
    }
}