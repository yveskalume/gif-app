package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.data.model.server.GiphyItem

object StickerEntityMapper : Mapper<GiphyItem, StickerEntity>() {
		override fun map(input: GiphyItem): StickerEntity {
				return with(input) {
						StickerEntity(
								id = id,
								title = title,
								imageUrl = images.fixed_height.url,
								createdAt = System.currentTimeMillis(),
								updatedAt = System.currentTimeMillis()
						)
				}
		}
}