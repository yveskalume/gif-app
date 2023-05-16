package com.yvkalume.gifapp.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.ui.components.EmptyView
import com.yvkalume.gifapp.ui.components.GifListView
import com.yvkalume.gifapp.ui.components.LoadingView
import com.yvkalume.gifapp.ui.screen.favorite.logic.FavoriteUiState
import com.yvkalume.gifapp.ui.screen.favorite.logic.FavoriteViewModel

@Composable
fun FavoriteRoute(homeViewModel: FavoriteViewModel = mavericksViewModel()) {
    val gifsState by homeViewModel.collectAsState(FavoriteUiState::gifs)
    FavoriteScreen(gifsState = gifsState, onFavoriteClick = { homeViewModel.removeFavorite(it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    gifsState: Async<List<Gif>>,
    onFavoriteClick: (Gif) -> Unit
) {

    Box(contentAlignment = Alignment.Center) {
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