package com.yvkalume.gifapp.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yvkalume.gifapp.data.model.room.GifEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {

    @Query("SELECT * FROM gifs ORDER BY createdAt DESC")
    fun getAll(): Flow<List<GifEntity>>

    @Query("SELECT * FROM gifs ORDER BY createdAt DESC")
    fun getAllPaginated(): PagingSource<Int, GifEntity>

    @Query("SELECT * FROM gifs WHERE id=:id LIMIT 1")
    fun findById(id: String): GifEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg gif: GifEntity)

    @Update
    fun update(gif: GifEntity)

    @Query("SELECT * FROM gifs WHERE isFavorite = true ORDER BY updatedAt DESC")
    fun getFavorites(): Flow<List<GifEntity>>

    @Query("SELECT * FROM gifs WHERE isFavorite = true ORDER BY createdAt DESC")
    fun getFavoritesPaginated(): PagingSource<Int, GifEntity>

}