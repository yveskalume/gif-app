package com.yvkalume.gifapp.data.repository

import android.util.Log
import com.yvkalume.gifapp.data.datasource.sticker.StickerLocalDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.StickerEntityMapper
import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.data.util.IoDispatcher
import com.yvkalume.gifapp.data.util.Result
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StickerRepository @Inject constructor(
		private val remoteDataSource: StickerRemoteDataSource,
		private val localDataSource: StickerLocalDataSource,
		private val coroutineScope: CoroutineScope,
		@IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {

		private fun updateLocalCache() {
				coroutineScope.launch(coroutineDispatcher) {
						try {
								val response = remoteDataSource.getAllTrending()
								if (response.meta.status == 200) {
										val stickerEntities = StickerEntityMapper.mapList(response.data)
										localDataSource.insertAll(stickerEntities.toTypedArray())
								}
						} catch (t: Throwable) {
								Log.e("UpdateLocalCache",t.message.toString())
						}
				}
		}

		fun getAllTrending(): Flow<Result<List<StickerEntity>>> {
				updateLocalCache()
				return localDataSource.getAll().map { Result.Success(it) }
		}
}