package com.yvkalume.gifapp.ui.screen.home.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yvkalume.gifapp.data.model.entity.GifEntity
import com.yvkalume.gifapp.ui.components.GifItem
import com.yvkalume.gifapp.ui.screen.home.logic.HomeUiState

@Composable
fun GifListView(modifier: Modifier = Modifier, uiState: HomeUiState) {

		Crossfade(targetState = uiState) { state ->
				when (state) {
						is HomeUiState.Error -> {
								Text(text = state.message)
						}
						HomeUiState.Loading -> {
								CircularProgressIndicator()
						}
						is HomeUiState.Success<*> -> {
								val gifs = (state.data as? List<GifEntity>) ?: emptyList()
								LazyColumn(
										modifier = modifier.fillMaxSize(),
										content = {
												items(gifs) { gif ->
														GifItem(gif = gif)
												}
										}
								)
						}
				}
		}
}