package com.yvkalume.gifapp.data.model.mapper

import com.yvkalume.gifapp.data.model.room.StickerEntity
import com.yvkalume.gifapp.data.model.server.FixedHeight
import com.yvkalume.gifapp.data.model.server.GifImage
import com.yvkalume.gifapp.data.model.server.GiphyItem
import org.junit.Assert.assertEquals
import org.junit.Test

class StickerEntityMapperTest {

		val giphyItem = GiphyItem(
				id = "giphyitem2",
				title = "Giphy Item 2",
				images = GifImage(
						fixed_height = FixedHeight(url = "https://imageurl.com/image.png")
				)
		)

		val expectedGifEntity = StickerEntity(
				id = "giphyitem2",
				title = "Giphy Item 2",
				imageUrl = "https://imageurl.com/image.png"
		)

		@Test
		fun map() {
				val output = StickerEntityMapper.map(giphyItem)
				assertEquals(expectedGifEntity, output)
		}
}