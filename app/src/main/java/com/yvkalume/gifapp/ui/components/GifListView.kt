package com.yvkalume.gifapp.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yvkalume.gifapp.domain.entity.Gif

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifListView(
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