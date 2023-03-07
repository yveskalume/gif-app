package com.yvkalume.gifapp.ui.screen.home.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

		Crossfade(targetState = uiState) { state ->
				when (state) {
						is HomeUiState.Error -> {
								Text(text = state.message)
						}
						HomeUiState.Loading -> {
								CircularProgressIndicator()
						}
						is HomeUiState.Success<*> -> {
								val stickers = (state.data as? List<Sticker>) ?: emptyList()
								LazyColumn(
										modifier = modifier.fillMaxSize(),
										content = {
												items(stickers) { sticker ->
														StickerItem(
																sticker = sticker,
																onFavoriteClick = onFavoriteClick
														)
												}
										}
								)
						}
				}
		}
}