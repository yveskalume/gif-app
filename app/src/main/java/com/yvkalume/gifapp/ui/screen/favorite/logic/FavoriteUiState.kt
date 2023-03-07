package com.yvkalume.gifapp.ui.screen.favorite.logic

sealed interface FavoriteUiState {
		data class Success<T>(val data: List<T>) : FavoriteUiState
		data class Error(val message: String) : FavoriteUiState
		object Loading : FavoriteUiState
}