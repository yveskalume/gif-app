package com.yvkalume.gifapp.ui.screen.home.logic

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

class HomeViewModel @AssistedInject constructor(
    @Assisted initialState: HomeUiState,
    private val gifRepository: GifRepository,
) : MavericksViewModel<HomeUiState>(initialState) {

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            gifRepository.refresh()
            gifRepository.getAllTrending().execute {
                copy(gifs = it)
            }
        }
    }

    fun toggleFavorite(gif: Gif) {
        viewModelScope.launch {
            val updatedGif = gif.copy(isFavorite = !gif.isFavorite)
            gifRepository.update(updatedGif)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
        override fun create(state: HomeUiState): HomeViewModel
    }

    companion object :
        MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory()
}
