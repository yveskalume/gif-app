package com.yvkalume.gifapp.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yvkalume.gifapp.domain.entity.Gif

@Entity(tableName = "gifs")
data class GifEntity(
		@PrimaryKey
		val id: String,
		val title: String,
		val imageUrl: String,
		val isFavorite: Boolean = false
)

fun Gif.toEntity(): GifEntity {
		return GifEntity(
				id = id, title = title, imageUrl = imageUrl, isFavorite = isFavorite
		)
}
