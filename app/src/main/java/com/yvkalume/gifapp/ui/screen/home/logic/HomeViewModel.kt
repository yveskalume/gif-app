package com.yvkalume.gifapp.ui.screen.home.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yvkalume.gifapp.data.repository.GifRepository
import com.yvkalume.gifapp.data.repository.StickerRepository
import com.yvkalume.gifapp.data.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
		gifRepository: GifRepository,
		stickerRepository: StickerRepository
) : ViewModel() {

		val gifs: StateFlow<HomeUiState> = gifRepository.getAllTrending().map { result ->
				when (result) {
						is Result.Error -> HomeUiState.Error(result.exception.message.toString())
						is Result.Success -> HomeUiState.Success(result.data)
				}
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5_000L),
				initialValue = HomeUiState.Loading
		)

		val stickers: StateFlow<HomeUiState> = stickerRepository.getAllTrending().map { result ->
				when (result) {
						is Result.Error -> HomeUiState.Error(result.exception.message.toString())
						is Result.Success -> HomeUiState.Success(result.data)
				}
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5_000L),
				initialValue = HomeUiState.Loading
		)
}