package com.yvkalume.gifapp.ui.screen.favorite.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.GifRepository
import com.yvkalume.gifapp.domain.repository.StickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val gifRepository: GifRepository,
    private val stickerRepository: StickerRepository
) : ViewModel() {

    val favoritesGifs: Flow<PagingData<Gif>>
        get() = gifRepository.getFavorites()

    val favoritesStickers: Flow<PagingData<Sticker>>
        get() = stickerRepository.getFavorites()


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