package com.yvkalume.gifapp.data.repository

import android.util.Log
import com.yvkalume.gifapp.data.datasource.gif.GifLocalDataSource
import com.yvkalume.gifapp.data.datasource.gif.GifRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.GifEntityMapper
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.util.IoDispatcher
import com.yvkalume.gifapp.data.util.Result
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GifRepository @Inject constructor(
		private val remoteDataSource: GifRemoteDataSource,
		private val localDataSource: GifLocalDataSource,
		private val coroutineScope: CoroutineScope,
		@IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {

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

		fun getAllTrending(): Flow<Result<List<GifEntity>>> {
				updateLocalCache()
				return localDataSource.getAll().map { Result.Success(it) }
		}
}