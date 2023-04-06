package com.yvkalume.gifapp.ui.components

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.yvkalume.gifapp.domain.entity.Sticker

@Composable
fun StickerListView(
    modifier: Modifier = Modifier,
    stickerItems: () -> LazyPagingItems<Sticker>,
    onFavoriteClick: (Sticker) -> Unit
) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (stickerItems().loadState.refresh) {
            LoadState.Loading -> {
                LoadingView()
            }
            is LoadState.Error -> {
                Log.e("Here",stickerItems().loadState.refresh.toString())
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
    stickers: () -> LazyPagingItems<Sticker>,
    onFavoriteClick: (Sticker) -> Unit,
    modifier: Modifier = Modifier
) {
    if (stickers().itemCount == 0) {
        EmptyView()
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            content = {
                items(items = stickers(), key = { it.id }) { sticker ->
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
                if (stickers().loadState.append is LoadState.Loading) {
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        )
    }
}