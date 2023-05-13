package com.yvkalume.gifapp.ui.screen.favorite.logic

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.yvkalume.gifapp.di.mavericks.AssistedViewModelFactory
import com.yvkalume.gifapp.di.mavericks.hiltMavericksViewModelFactory
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.repository.GifRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class FavoriteViewModel @AssistedInject constructor(
    @Assisted initialState: FavoriteUiState,
    private val gifRepository: GifRepository,
) : MavericksViewModel<FavoriteUiState>(initialState) {

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            gifRepository.getFavorites().execute {
                copy(gifs = it)
            }
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
