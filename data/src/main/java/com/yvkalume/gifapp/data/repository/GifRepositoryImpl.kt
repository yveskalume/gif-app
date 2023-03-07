package com.yvkalume.gifapp.data.repository

import android.util.Log
import com.yvkalume.gifapp.data.datasource.gif.GifLocalDataSource
import com.yvkalume.gifapp.data.datasource.gif.GifRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.GifEntityMapper
import com.yvkalume.gifapp.data.model.mapper.GifMapper
import com.yvkalume.gifapp.data.model.room.toEntity
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.repository.GifRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GifRepositoryImpl @Inject constructor(
		private val remoteDataSource: GifRemoteDataSource,
		private val localDataSource: GifLocalDataSource,
		private val coroutineScope: CoroutineScope,
		private val coroutineDispatcher: CoroutineDispatcher
) : GifRepository {

		private fun updateLocalCache() {
				coroutineScope.launch(coroutineDispatcher) {
						try {
								val response = remoteDataSource.getAllTrending()
								if (response.meta.status == 200) {
										val gifEntities = GifEntityMapper.mapList(response.data)
										localDataSource.insertAll(gifEntities.toTypedArray())
								}
						} catch (t: Throwable) {
								Log.e("UpdateLocalCache", t.message.toString())
						}
				}
		}

		override fun getAllTrending(): Flow<List<Gif>> {
				updateLocalCache()
				return localDataSource.getAll().map { GifMapper.mapList(it) }
		}

		override suspend fun update(gif: Gif) {
				coroutineScope.launch(coroutineDispatcher) {
						localDataSource.update(gif.toEntity())
				}
		}
}