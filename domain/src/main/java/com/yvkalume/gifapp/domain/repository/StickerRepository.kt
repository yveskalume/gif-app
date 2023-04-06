package com.yvkalume.gifapp.domain.repository

import androidx.paging.PagingData
import com.yvkalume.gifapp.domain.entity.Sticker
import kotlinx.coroutines.flow.Flow

interface StickerRepository {
    fun getAllTrending(): Flow<List<Sticker>>
    suspend fun update(sticker: Sticker)
    fun getFavorites(): Flow<List<Sticker>>
    suspend fun refresh()
}