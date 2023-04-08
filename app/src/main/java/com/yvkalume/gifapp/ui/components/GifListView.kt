package com.yvkalume.gifapp.ui.components

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.yvkalume.gifapp.domain.entity.Gif

@Composable
fun GifListView(
    modifier: Modifier = Modifier,
    gifItems: LazyPagingItems<Gif>,
    onFavoriteClick: (Gif) -> Unit
) {

    var isFirstLoad by remember {
        mutableStateOf(true)
    }

    val itemsLoadState by remember {
        derivedStateOf {
            gifItems.loadState.source.refresh
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when {
            (itemsLoadState is LoadState.Loading && isFirstLoad) -> {
                isFirstLoad = false
                LoadingView()
            }
            itemsLoadState is LoadState.Error -> {
                EmptyView()
            }
            else -> {
                GifListViewContent(
                    modifier = modifier,
                    gifs = gifItems,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun GifListViewContent(
    modifier: Modifier = Modifier,
    gifs: LazyPagingItems<Gif>,
    onFavoriteClick: (Gif) -> Unit
) {
    val listState = rememberLazyListState()
    if (gifs.itemCount == 0) {
        EmptyView()
    } else {
        LazyColumn(
            state = listState,
            modifier = modifier.fillMaxSize(),
            content = {
                items(items = gifs, key = { it.id }) { gif ->
                    if (gif != null) {
                        GifItem(
                            gif = gif,
                            onFavoriteClick = onFavoriteClick,
                            modifier = Modifier.wrapContentHeight().animateItemPlacement(
                                animationSpec = tween(2000)
                            )
                        )
                    }
                }
                if (gifs.loadState.append is LoadState.Loading) {
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        )
    }
}