package com.yvkalume.gifapp.data.repository

import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.mapper.StickerEntityMapper
import com.yvkalume.gifapp.data.util.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class StickerRepository @Inject constructor(private val remoteDataSource: StickerRemoteDataSource) {

		fun getAllTrending() = flow {
				val response = remoteDataSource.getAllTrending()

				if (response.meta.status != 200) {
						emit(Result.Error(Exception(response.meta.msg)))
				} else {
						val stickerEntities = StickerEntityMapper.mapList(response.data)
						emit(Result.Success(stickerEntities))
				}
		}.catch {
				emit(Result.Error(Exception(it.localizedMessage)))
		}
}