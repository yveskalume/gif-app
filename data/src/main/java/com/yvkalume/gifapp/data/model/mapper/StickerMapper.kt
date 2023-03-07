package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.domain.entity.Sticker

object StickerMapper : Mapper<StickerEntity, Sticker>() {
		override fun map(input: StickerEntity): Sticker {
				return with(input) {
						Sticker(
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