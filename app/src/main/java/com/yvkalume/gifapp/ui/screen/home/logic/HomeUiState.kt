package com.yvkalume.gifapp.ui.screen.home.logic

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.yvkalume.gifapp.domain.entity.Gif

data class HomeUiState(
    val gifs: Async<List<Gif>> = Loading(),
) : MavericksState