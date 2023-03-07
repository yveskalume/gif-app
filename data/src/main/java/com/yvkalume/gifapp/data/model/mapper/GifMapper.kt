package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.domain.entity.Gif

object GifMapper : Mapper<GifEntity, Gif>() {
		override fun map(input: GifEntity): Gif {
				return with(input) {
						Gif(
								id = id,
								title = title,
								imageUrl = imageUrl,
								isFavorite = isFavorite,
								createdAt = createdAt,
								updatedAt = updatedAt
						)
				}
		}
}