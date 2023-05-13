package com.yvkalume.gifapp.ui.screen.favorite.logic

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.yvkalume.gifapp.domain.entity.Gif

data class FavoriteUiState(
    val gifs: Async<List<Gif>> = Loading(),
) : MavericksState