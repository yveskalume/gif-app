package com.yvkalume.gifapp.data.datasource.gif

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yvkalume.gifapp.data.model.mapper.GifMapper
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.room.dao.GifDao
import com.yvkalume.gifapp.domain.entity.Gif
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifLocalDataSource @Inject constructor(private val gifDao: GifDao) {
    fun getAll(): Flow<List<GifEntity>> = gifDao.getAll()

    fun getAllPaginated(): PagingSource<Int, Gif> {
        return object : PagingSource<Int, Gif>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
                val page = params.key ?: 0
                return try {
                    val data = gifDao.getAllPaginated(params.loadSize, page * params.loadSize)
                    LoadResult.Page(
                        data = data.map { GifMapper.map(it) },
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

        }
    }

    fun findById(id: String): GifEntity = gifDao.findById(id)

    suspend fun insertAll(gif: Array<GifEntity>) = gifDao.insert(*gif)

    suspend fun insert(sticker: GifEntity) = gifDao.insert(sticker)

    fun update(sticker: GifEntity) = gifDao.update(sticker)

    fun getFavorites(): Flow<List<GifEntity>> = gifDao.getFavorites()

    fun getFavoritesPaginated(): PagingSource<Int, Gif> {
        return object : PagingSource<Int, Gif>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
                val page = params.key ?: 0
                return try {
                    val data = gifDao.getFavoritesPaginated(params.loadSize, page * params.loadSize)
                    LoadResult.Page(
                        data = data.map { GifMapper.map(it) },
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

        }
    }
}