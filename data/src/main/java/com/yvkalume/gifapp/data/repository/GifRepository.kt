package com.yvkalume.gifapp.data.repository

import com.yvkalume.gifapp.data.datasource.gif.GifRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.GifEntityMapper
import com.yvkalume.gifapp.data.util.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GifRepository @Inject constructor(private val remoteDataSource: GifRemoteDataSource) {

		fun getAllTrending() = flow {
				val response = remoteDataSource.getAllTrending()

				if (response.meta.status != 200) {
						emit(Result.Error(Exception(response.meta.msg)))
				} else {
						val gifEntities = GifEntityMapper.mapList(response.data)
						emit(Result.Success(gifEntities))
				}
		}.catch {
				emit(Result.Error(Exception(it.localizedMessage)))
		}
}