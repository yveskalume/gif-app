package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.entity.GifEntity
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
				imageUrl = "https://imageurl.com/image.png"
		)

		@Test
		fun map() {
				val output = GifEntityMapper.map(giphyItem)
				assertEquals(expectedGifEntity,output)
		}
}