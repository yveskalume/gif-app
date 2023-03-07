package com.yvkalume.gifapp.domain.repository

import com.yvkalume.gifapp.domain.entity.Gif
import kotlinx.coroutines.flow.Flow

interface GifRepository {
		fun getAllTrending(): Flow<List<Gif>>
}