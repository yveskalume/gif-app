package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.model.server.GiphyItem
import com.yvkalume.gifapp.domain.entity.Gif

object GifEntityMapper : Mapper<GiphyItem, GifEntity>() {
		override fun map(input: GiphyItem): GifEntity {
				return with(input) {
						GifEntity(
								id = id,
								title = title,
								imageUrl = images.fixed_height.url
						)
				}
		}

		fun map(input: Gif) : GifEntity {
				return with(input) {
						GifEntity(
								id = id,
								title = title,
								imageUrl = imageUrl,
								isFavorite = isFavorite
						)
				}
		}
}