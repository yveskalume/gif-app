package com.yvkalume.gifapp.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.gifapp.domain.entity.Gif

@Composable
fun GifListView(
    modifier: Modifier = Modifier,
    gifsState: Async<List<Gif>>,
    onFavoriteClick: (Gif) -> Unit
) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
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
                    GifListViewContent(
                        modifier = modifier,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun GifListViewContent(
    modifier: Modifier = Modifier,
    gifs: List<Gif>,
    onFavoriteClick: (Gif) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        content = {
            items(items = gifs, key = { it.id }) { gif ->
                GifItem(
                    gif = gif,
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier
                        .wrapContentHeight()
                        .animateItemPlacement(
                            animationSpec = tween(2000)
                        )
                )
            }
        }
    )
}