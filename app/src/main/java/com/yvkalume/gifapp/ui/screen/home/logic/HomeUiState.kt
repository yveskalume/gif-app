package com.yvkalume.gifapp.ui.screen.home.logic

sealed interface HomeUiState {
		data class Success<T>(val data: List<T>) : HomeUiState
		data class Error(val message: String) : HomeUiState
		object Loading : HomeUiState
}