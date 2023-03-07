package com.yvkalume.gifapp.data.datasource.sticker

import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.data.room.dao.StickerDao
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StickerLocalDataSource @Inject constructor(private val stickerDao: StickerDao) {

		fun getAll(): Flow<List<StickerEntity>> = stickerDao.getAll()

		fun findById(id: String): StickerEntity = stickerDao.findById(id)

		suspend fun insertAll(stickers: Array<StickerEntity>) = stickerDao.insert(*stickers)

		suspend fun insert(sticker: StickerEntity) = stickerDao.insert(sticker)

		fun update(sticker: StickerEntity) = stickerDao.update(sticker)

		fun getFavorites(): Flow<List<StickerEntity>> = stickerDao.getFavorites()

}