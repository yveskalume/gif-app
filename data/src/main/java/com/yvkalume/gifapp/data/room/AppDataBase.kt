package com.yvkalume.gifapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.room.dao.GifDao

@Database(entities = [GifEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}