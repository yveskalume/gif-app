package com.yvkalume.gifapp.ui.screen.home.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.GifRepository
import com.yvkalume.gifapp.domain.repository.StickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gifRepository: GifRepository,
    private val stickerRepository: StickerRepository
) : ViewModel() {

    init {
        refreshStickers()
        refreshGifs()
    }

    val gifs: Flow<PagingData<Gif>>
        get() = gifRepository.getAllTrending()

    val stickers: Flow<PagingData<Sticker>>
        get() = stickerRepository.getAllTrending()

    fun toggleFavorite(sticker: Sticker) {
        viewModelScope.launch {
            val updatedSticker = sticker.copy(isFavorite = !sticker.isFavorite)
            stickerRepository.update(updatedSticker)
        }
    }

    fun toggleFavorite(gif: Gif) {
        viewModelScope.launch {
            val updatedGif = gif.copy(isFavorite = !gif.isFavorite)
            gifRepository.update(updatedGif)
        }
    }

    private fun refreshGifs() {
        viewModelScope.launch {
            gifRepository.refresh()
        }
    }

    private fun refreshStickers() {
        viewModelScope.launch {
            stickerRepository.refresh()
        }
    }
}
