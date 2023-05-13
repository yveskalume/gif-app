package com.yvkalume.gifapp.data.repository

import com.yvkalume.gifapp.data.datasource.GifLocalDataSource
import com.yvkalume.gifapp.data.datasource.GifRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.GifEntityMapper
import com.yvkalume.gifapp.data.model.mapper.GifMapper
import com.yvkalume.gifapp.data.model.room.toEntity
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.repository.GifRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val remoteDataSource: GifRemoteDataSource,
    private val localDataSource: GifLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : GifRepository {

    override fun getAllTrending(): Flow<List<Gif>> {
        return localDataSource.getAll().map { GifMapper.mapList(it) }
    }

    override suspend fun update(gif: Gif) {
        val updatedGif = gif.copy(updatedAt = System.currentTimeMillis())
        withContext(coroutineDispatcher) {
            localDataSource.update(updatedGif.toEntity())
        }
    }

    override fun getFavorites(): Flow<List<Gif>> {
        return localDataSource.getFavorites().map { GifMapper.mapList(it) }
    }

    override suspend fun refresh() {
        withContext(coroutineDispatcher) {
            try {
                val response = remoteDataSource.getAllTrending()
                if (response.meta.status == 200) {
                    val gifEntities = GifEntityMapper.mapList(response.data)
                    localDataSource.insertAll(gifEntities.toTypedArray())
                }
            } catch (t: Throwable) {
                Timber.e(t.message.toString())
            }
        }
    }
}