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
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.ui.screen.home.logic.HomeUiState

@Composable
fun GifListView(
		modifier: Modifier = Modifier,
		uiState: HomeUiState,
		onFavoriteClick: (Gif) -> Unit
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
								val gifs = (uiState.data as? List<Gif>) ?: emptyList()
								GifListViewContent(
										modifier = modifier,
										gifs = gifs,
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
		gifs: List<Gif>,
		onFavoriteClick: (Gif) -> Unit
) {
		if (gifs.isEmpty()) {
				EmptyView()
		} else {
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