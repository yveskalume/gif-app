package com.yvkalume.gifapp.data.repository

import com.yvkalume.gifapp.data.datasource.sticker.StickerLocalDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.StickerEntityMapper
import com.yvkalume.gifapp.data.model.mapper.StickerMapper
import com.yvkalume.gifapp.data.model.room.toEntity
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.StickerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class StickerRepositoryImpl @Inject constructor(
    private val remoteDataSource: StickerRemoteDataSource,
    private val localDataSource: StickerLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : StickerRepository {

    override fun getAllTrending(): Flow<List<Sticker>> {
        return localDataSource.getAll().map { StickerMapper.mapList(it) }
    }

    override suspend fun update(sticker: Sticker) {
        val updatedSticker = sticker.copy(updatedAt = System.currentTimeMillis())
        withContext(coroutineDispatcher) {
            localDataSource.update(updatedSticker.toEntity())
        }
    }

    override fun getFavorites(): Flow<List<Sticker>> {
        return localDataSource.getFavorites().map { StickerMapper.mapList(it) }
    }

    override suspend fun refresh() {
        withContext(coroutineDispatcher) {
            try {
                val response = remoteDataSource.getAllTrending()
                if (response.meta.status == 200) {
                    val stickerEntities = StickerEntityMapper.mapList(response.data)
                    localDataSource.insertAll(stickerEntities.toTypedArray())
                }
            } catch (t: Throwable) {
                Timber.e(t.message.toString())
            }
        }
    }
}