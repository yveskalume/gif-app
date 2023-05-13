package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.model.server.GiphyItem

object GifEntityMapper : Mapper<GiphyItem, GifEntity>() {
    override fun map(input: GiphyItem): GifEntity {
        return with(input) {
            GifEntity(
                id = id,
                title = title,
                imageUrl = images.fixed_height.url,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        }
    }
}