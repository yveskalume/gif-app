package com.yvkalume.gifapp.ui.screen.home.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yvkalume.gifapp.domain.repository.GifRepository
import com.yvkalume.gifapp.domain.repository.StickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
		gifRepository: GifRepository,
		stickerRepository: StickerRepository
) : ViewModel() {

		val gifs: StateFlow<HomeUiState> = gifRepository.getAllTrending().map { data ->
				HomeUiState.Success(data)
		}.catch {
				HomeUiState.Error(it.message.toString())
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5_000L),
				initialValue = HomeUiState.Loading
		)


		val stickers: StateFlow<HomeUiState> = stickerRepository.getAllTrending().map { data ->
				HomeUiState.Success(data)
		}.catch {
				HomeUiState.Error(it.message.toString())
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5_000L),
				initialValue = HomeUiState.Loading
		)

}
