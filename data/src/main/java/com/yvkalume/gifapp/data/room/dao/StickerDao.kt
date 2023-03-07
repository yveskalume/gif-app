package com.yvkalume.gifapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.model.room.StickerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StickerDao {

		@Query("SELECT * FROM stickers ORDER BY createdAt DESC")
		fun getAll() : Flow<List<StickerEntity>>

		@Query("SELECT * FROM stickers WHERE id=:id LIMIT 1")
		fun findById(id: String) : StickerEntity

		@Insert(onConflict = OnConflictStrategy.IGNORE)
		suspend fun insert(vararg sticker: StickerEntity)

		@Update
		fun update(sticker: StickerEntity)

		@Query("SELECT * FROM stickers WHERE isFavorite = true ORDER BY updatedAt DESC")
		fun getFavorites(): Flow<List<StickerEntity>>
}