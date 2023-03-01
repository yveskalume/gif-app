package com.yvkalume.gifapp.ui.screen.home.logic

import com.yvkalume.gifapp.data.model.server.Gif

sealed interface HomeUiState {
		data class Success(val data: List<Gif>) : HomeUiState
		data class Error(val message: String) : HomeUiState
		object Loading : HomeUiState
}