package com.yvkalume.gifapp.data.datasource

import androidx.paging.PagingSource
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.room.dao.GifDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifLocalDataSource @Inject constructor(private val gifDao: GifDao) {
    fun getAll(): Flow<List<GifEntity>> = gifDao.getAll()

    fun getAllPaginated(): PagingSource<Int, GifEntity> = gifDao.getAllPaginated()

    fun findById(id: String): GifEntity = gifDao.findById(id)

    suspend fun insertAll(gif: Array<GifEntity>) = gifDao.insert(*gif)

    suspend fun insert(sticker: GifEntity) = gifDao.insert(sticker)

    fun update(sticker: GifEntity) = gifDao.update(sticker)

    fun getFavorites(): Flow<List<GifEntity>> = gifDao.getFavorites()

    fun getFavoritesPaginated(): PagingSource<Int, GifEntity> = gifDao.getFavoritesPaginated()
}