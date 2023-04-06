package com.yvkalume.gifapp.ui.screen.home.logic

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.entity.Sticker

data class HomeUiState(
    val gifs: Async<List<Gif>> = Uninitialized,
    val stickers: Async<List<Sticker>> = Uninitialized,
) : MavericksState