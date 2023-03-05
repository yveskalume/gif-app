package com.yvkalume.gifapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.data.room.dao.GifDao
import com.yvkalume.gifapp.data.room.dao.StickerDao

@Database(entities = [GifEntity::class, StickerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
		abstract fun gifDao(): GifDao
		abstract fun stickerDao(): StickerDao
}