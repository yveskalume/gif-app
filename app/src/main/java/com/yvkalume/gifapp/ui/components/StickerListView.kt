package com.yvkalume.gifapp.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.yvkalume.gifapp.domain.entity.Sticker

@Composable
fun StickerListView(
    modifier: Modifier = Modifier,
    stickersState: Async<List<Sticker>>,
    onFavoriteClick: (Sticker) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (stickersState) {
            is Fail -> {
                EmptyView()
            }
            is Loading -> {
                LoadingView()
            }
            is Success -> {
                StickerListViewContent(
                    modifier = modifier,
                    stickers = stickersState.invoke(),
                    onFavoriteClick = onFavoriteClick
                )
            }
            Uninitialized -> {}
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StickerListViewContent(
    stickers: List<Sticker>,
    onFavoriteClick: (Sticker) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        content = {
            items(items = stickers, key = { it.id }) { sticker ->
                StickerItem(
                    sticker = sticker,
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(2000)
                    )
                )
            }
        }
    )
}