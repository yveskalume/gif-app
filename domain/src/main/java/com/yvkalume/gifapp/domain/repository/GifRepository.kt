package com.yvkalume.gifapp.domain.repository

import androidx.paging.PagingData
import com.yvkalume.gifapp.domain.entity.Gif
import kotlinx.coroutines.flow.Flow

interface GifRepository {
		fun getAllTrending(): Flow<PagingData<Gif>>
		suspend fun update(gif: Gif)
		fun getFavorites(): Flow<PagingData<Gif>>
		suspend fun refresh()
}