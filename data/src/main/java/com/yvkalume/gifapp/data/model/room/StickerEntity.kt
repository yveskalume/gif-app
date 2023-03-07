package com.yvkalume.gifapp.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yvkalume.gifapp.domain.entity.Sticker

@Entity(tableName = "stickers")
data class StickerEntity(
		@PrimaryKey
		val id: String,
		val title: String,
		val imageUrl: String,
		val isFavorite: Boolean = false
)


fun Sticker.toEntity(): StickerEntity {
		return StickerEntity(
				id = id, title = title, imageUrl = imageUrl, isFavorite = isFavorite
		)
}
