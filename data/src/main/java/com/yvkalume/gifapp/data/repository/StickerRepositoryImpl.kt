package com.yvkalume.gifapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.yvkalume.gifapp.data.datasource.sticker.StickerLocalDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.StickerEntityMapper
import com.yvkalume.gifapp.data.model.mapper.StickerMapper
import com.yvkalume.gifapp.data.model.room.toEntity
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.StickerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class StickerRepositoryImpl @Inject constructor(
    private val remoteDataSource: StickerRemoteDataSource,
    private val localDataSource: StickerLocalDataSource,
    private val coroutineScope: CoroutineScope,
    private val coroutineDispatcher: CoroutineDispatcher
) : StickerRepository {

    override fun getAllTrending(): Flow<PagingData<Sticker>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { localDataSource.getAllPaginated() }
        ).flow.map { it.map(StickerMapper::map) }.flowOn(coroutineDispatcher)
    }

    override suspend fun update(sticker: Sticker) {
        val updatedSticker = sticker.copy(updatedAt = System.currentTimeMillis())
        coroutineScope.launch(coroutineDispatcher) {
            localDataSource.update(updatedSticker.toEntity())
        }
    }

    override fun getFavorites(): Flow<PagingData<Sticker>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { localDataSource.getFavoritesPaginated() }
        ).flow.map { it.map(StickerMapper::map) }.flowOn(coroutineDispatcher)
    }

    override suspend fun refresh() {
        coroutineScope.launch(coroutineDispatcher) {
            try {
                val response = remoteDataSource.getAllTrending()
                if (response.meta.status == 200) {
                    val stickerEntities = StickerEntityMapper.mapList(response.data)
                    localDataSource.insertAll(stickerEntities.toTypedArray())
                }
            } catch (t: Throwable) {
                Log.e("UpdateLocalCache", t.message.toString())
            }
        }
    }
}