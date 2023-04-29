package com.yvkalume.gifapp.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yvkalume.gifapp.domain.entity.Gif

@Entity(tableName = "gifs")
data class GifEntity(
		@PrimaryKey
		@ColumnInfo(name = "id")
		val id: String,
		@ColumnInfo(name = "title")
		val title: String,
		@ColumnInfo(name = "imageUrl")
		val imageUrl: String,
		@ColumnInfo(name = "isFavorite")
		val isFavorite: Boolean = false,
		@ColumnInfo(name = "createdAt")
		val createdAt: Long,
		@ColumnInfo(name = "updatedAt")
		val updatedAt: Long
)

fun Gif.toEntity(): GifEntity {
		return GifEntity(
				id = id,
				title = title,
				imageUrl = imageUrl,
				isFavorite = isFavorite,
				createdAt = createdAt,
				updatedAt = updatedAt
		)
}
