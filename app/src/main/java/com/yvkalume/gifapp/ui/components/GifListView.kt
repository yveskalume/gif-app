package com.yvkalume.gifapp.ui.screen.home.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.ui.components.GifItem
import com.yvkalume.gifapp.ui.screen.home.logic.HomeUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifListView(
		modifier: Modifier = Modifier,
		uiState: HomeUiState,
		onFavoriteClick: (Gif) -> Unit
) {

		when (uiState) {
				is HomeUiState.Error -> {
						Text(text = uiState.message)
				}
				HomeUiState.Loading -> {
						CircularProgressIndicator()
				}
				is HomeUiState.Success<*> -> {
						val gifs = (uiState.data as? List<Gif>) ?: emptyList()
						LazyColumn(
								modifier = modifier.fillMaxSize(),
								content = {
										items(items = gifs, key = { it.id }) { gif ->
												GifItem(
														gif = gif,
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

}