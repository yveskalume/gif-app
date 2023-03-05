package com.yvkalume.gifapp.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stickers")
data class StickerEntity(
		@PrimaryKey
		val id: String,
		val title: String,
		val imageUrl: String,
		val isFavorite: Boolean = false
)
