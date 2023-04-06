package com.yvkalume.gifapp.data.datasource.sticker

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yvkalume.gifapp.data.model.mapper.StickerMapper
import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.data.room.dao.StickerDao
import com.yvkalume.gifapp.domain.entity.Sticker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StickerLocalDataSource @Inject constructor(private val stickerDao: StickerDao) {

    fun getAll(): Flow<List<StickerEntity>> = stickerDao.getAll()

    fun getAllPaginated(): PagingSource<Int, Sticker> {
        return object : PagingSource<Int, Sticker>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Sticker> {
                val page = params.key ?: 0
                return try {
                    val data = stickerDao.getAllPaginated(params.loadSize, page * params.loadSize)

                    LoadResult.Page(
                        data = data.map { StickerMapper.map(it) },
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Sticker>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

        }
    }

    fun findById(id: String): StickerEntity = stickerDao.findById(id)

    suspend fun insertAll(stickers: Array<StickerEntity>) = stickerDao.insert(*stickers)

    suspend fun insert(sticker: StickerEntity) = stickerDao.insert(sticker)

    fun update(sticker: StickerEntity) = stickerDao.update(sticker)

    fun getFavorites(): Flow<List<StickerEntity>> = stickerDao.getFavorites()

    fun getFavoritesPaginated(): PagingSource<Int, Sticker> {
        return object : PagingSource<Int, Sticker>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Sticker> {
                val page = params.key ?: 0
                return try {
                    val data = stickerDao
                        .getFavoritesPaginated(params.loadSize, page * params.loadSize)

                    LoadResult.Page(
                        data = data.map { StickerMapper.map(it) },
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Sticker>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

        }
    }

}