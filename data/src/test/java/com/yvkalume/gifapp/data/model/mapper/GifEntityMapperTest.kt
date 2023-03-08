package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.model.server.FixedHeight
import com.yvkalume.gifapp.data.model.server.GifImage
import com.yvkalume.gifapp.data.model.server.GiphyItem
import org.junit.Assert.*

import org.junit.Test

class GifEntityMapperTest {

		val giphyItem = GiphyItem(
				id = "giphyitem1",
				title = "Giphy Item 1",
				images = GifImage(
						fixed_height = FixedHeight(url = "https://imageurl.com/image.png")
				)
		)

		val expectedGifEntity = GifEntity(
				id = "giphyitem1",
				title = "Giphy Item 1",
				imageUrl = "https://imageurl.com/image.png",
				createdAt = 100000,
				updatedAt = 100000
		)

		@Test
		fun map() {
				val output = GifEntityMapper.map(giphyItem)
				assertEquals(expectedGifEntity.id,output.id)
		}
}