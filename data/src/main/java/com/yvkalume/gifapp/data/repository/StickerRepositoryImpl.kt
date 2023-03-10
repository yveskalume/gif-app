package com.yvkalume.gifapp.data.repository

import android.util.Log
import com.yvkalume.gifapp.data.datasource.sticker.StickerLocalDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.StickerEntityMapper
import com.yvkalume.gifapp.data.model.mapper.StickerMapper
import com.yvkalume.gifapp.data.model.room.toEntity
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.domain.repository.StickerRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StickerRepositoryImpl @Inject constructor(
		private val remoteDataSource: StickerRemoteDataSource,
		private val localDataSource: StickerLocalDataSource,
		private val coroutineScope: CoroutineScope,
		private val coroutineDispatcher: CoroutineDispatcher
) : StickerRepository {

		private fun updateLocalCache() {
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

		override fun getAllTrending(): Flow<List<Sticker>> {
				updateLocalCache()
				return localDataSource.getAll().map { StickerMapper.mapList(it) }
		}

		override suspend fun update(sticker: Sticker) {
				val updatedSticker = sticker.copy(updatedAt = System.currentTimeMillis())
				coroutineScope.launch(coroutineDispatcher) {
						localDataSource.update(updatedSticker.toEntity())
				}
		}

		override fun getFavorites(): Flow<List<Sticker>> {
				return localDataSource.getFavorites().map { StickerMapper.mapList(it) }
		}
}