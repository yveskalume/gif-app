package com.yvkalume.gifapp.data.datasource.gif

import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.room.dao.GifDao
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GifLocalDataSource @Inject constructor(private val gifDao: GifDao) {
		fun getAll(): Flow<List<GifEntity>> = gifDao.getAll()

		fun findById(id: String): GifEntity = gifDao.findById(id)

		suspend fun insertAll(stickers: Array<GifEntity>) = gifDao.insert(*stickers)

		suspend fun insert(sticker: GifEntity) = gifDao.insert(sticker)

		fun update(sticker: GifEntity) = gifDao.update(sticker)

		fun getFavorites(): Flow<List<GifEntity>> = gifDao.getFavorites()
}