package com.yvkalume.gifapp.data.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
		override fun migrate(database: SupportSQLiteDatabase) {
				val timeStamp = System.currentTimeMillis()
				database.execSQL("ALTER TABLE gifs ADD COLUMN createdAt INTEGER NOT NULL DEFAULT $timeStamp")
				database.execSQL("ALTER TABLE gifs ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT $timeStamp")

				database.execSQL("ALTER TABLE stickers ADD COLUMN createdAt INTEGER NOT NULL DEFAULT $timeStamp")
				database.execSQL("ALTER TABLE stickers ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT $timeStamp")
		}
}