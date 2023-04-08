package com.yvkalume.gifapp.ui.components

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.yvkalume.gifapp.domain.entity.Sticker
import timber.log.Timber

@Composable
fun StickerListView(
    modifier: Modifier = Modifier,
    stickerItems: LazyPagingItems<Sticker>,
    onFavoriteClick: (Sticker) -> Unit
) {
    var isFirstLoad by remember {
        mutableStateOf(true)
    }

    val itemsLoadState by remember {
        derivedStateOf {
            stickerItems.loadState.source.refresh
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
                StickerListViewContent(
                    modifier = modifier,
                    stickers = stickerItems,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StickerListViewContent(
    stickers: LazyPagingItems<Sticker>,
    onFavoriteClick: (Sticker) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    if (stickers.itemCount == 0) {
        EmptyView()
    } else {
        LazyColumn(
            state = listState,
            modifier = modifier.fillMaxSize(),
            content = {
                items(items = stickers, key = { it.id }) { sticker ->
                    if (sticker != null) {
                        StickerItem(
                            sticker = sticker,
                            onFavoriteClick = onFavoriteClick,
                            modifier = Modifier.animateItemPlacement(
                                animationSpec = tween(2000)
                            )
                        )
                    }
                }
                if (stickers.loadState.append is LoadState.Loading) {
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        )
    }
}