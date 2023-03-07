package com.yvkalume.gifapp.domain.repository

import com.yvkalume.gifapp.domain.entity.Sticker
import kotlinx.coroutines.flow.Flow

interface StickerRepository {
		fun getAllTrending(): Flow<List<Sticker>>
		suspend fun update(sticker: Sticker)
}