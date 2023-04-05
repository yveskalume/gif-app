package com.yvkalume.gifapp.ui.screen.favorite.logic

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.gifapp.di.mavericks.AssistedViewModelFactory
import com.yvkalume.gifapp.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.GifRepository
import com.yvkalume.gifapp.domain.repository.StickerRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @AssistedInject constructor(
    @Assisted initialState: FavoriteUiState,
    private val gifRepository: GifRepository,
    private val stickerRepository: StickerRepository
) : MavericksViewModel<FavoriteUiState>(initialState) {

    init {
        getStickers()
        getGifs()
    }

    private fun getGifs() {
        gifRepository.getFavorites().execute {
            copy(gifs = it)
        }
    }

    private fun getStickers() {
        stickerRepository.getFavorites().execute {
            copy(stickers = it)
        }
    }

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

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<FavoriteViewModel, FavoriteUiState> {
        override fun create(state: FavoriteUiState): FavoriteViewModel
    }

    companion object :
        MavericksViewModelFactory<FavoriteViewModel, FavoriteUiState> by hiltMavericksViewModelFactory()

}