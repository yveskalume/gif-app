package com.yvkalume.gifapp.ui.screen.favorite.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.GifRepository
import com.yvkalume.gifapp.domain.repository.StickerRepository
import com.yvkalume.gifapp.ui.screen.home.logic.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FavoriteViewModel @Inject constructor(
		private val gifRepository: GifRepository,
		private val stickerRepository: StickerRepository
) : ViewModel() {

		val gifs: StateFlow<HomeUiState> = gifRepository.getFavorites().map { data ->
				HomeUiState.Success(data)
		}.catch {
				HomeUiState.Error(it.message.toString())
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5_000L),
				initialValue = HomeUiState.Loading
		)


		val stickers: StateFlow<HomeUiState> = stickerRepository.getFavorites().map { data ->
				HomeUiState.Success(data)
		}.catch {
				HomeUiState.Error(it.message.toString())
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5_000L),
				initialValue = HomeUiState.Loading
		)

		fun removerFavorite(sticker: Sticker) {
				viewModelScope.launch {
						val updatedSticker = sticker.copy(isFavorite = !sticker.isFavorite)
						stickerRepository.update(updatedSticker)
				}
		}
		fun removeFavorite(gif: Gif) {
				viewModelScope.launch {
						val updatedGif = gif.copy(isFavorite = !gif.isFavorite)
						gifRepository.update(updatedGif)
				}
		}

}