package com.yvkalume.gifapp.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.ui.components.StickerItem
import com.yvkalume.gifapp.ui.screen.home.logic.HomeUiState

@Composable
fun StickerListView(
		modifier: Modifier = Modifier,
		uiState: HomeUiState,
		onFavoriteClick: (Sticker) -> Unit
) {

		Box(modifier = modifier, contentAlignment = Alignment.Center) {
				when (uiState) {
						is HomeUiState.Error -> {
								EmptyView()
						}
						HomeUiState.Loading -> {
								LoadingView()
						}
						is HomeUiState.Success<*> -> {
								val stickers = (uiState.data as? List<Sticker>) ?: emptyList()
								StickerListViewContent(
										modifier = modifier,
										stickers = stickers,
										onFavoriteClick = onFavoriteClick
								)
						}
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
		if (stickers.isEmpty()) {
				EmptyView()
		} else {
				LazyColumn(
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
}