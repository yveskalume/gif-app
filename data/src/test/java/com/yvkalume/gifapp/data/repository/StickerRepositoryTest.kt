package com.yvkalume.gifapp.data.repository

import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.server.FixedHeight
import com.yvkalume.gifapp.data.model.server.GifImage
import com.yvkalume.gifapp.data.model.server.GiphyHttpResponse
import com.yvkalume.gifapp.data.model.server.GiphyItem
import com.yvkalume.gifapp.data.model.server.MetaObject
import com.yvkalume.gifapp.data.model.server.PaginationObject
import com.yvkalume.gifapp.data.util.data
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StickerRepositoryTest {

		@get:Rule
		val mockKRule = MockKRule(this)

		@MockK
		lateinit var stickerRemoteDataSource: StickerRemoteDataSource

		private lateinit var stickerRepository: StickerRepository

		@Before
		fun setUp() {
				stickerRepository = StickerRepository(stickerRemoteDataSource)
		}

		@OptIn(ExperimentalCoroutinesApi::class)
		@Test
		fun `should get Result Error when an Exception is thrown`() = runTest {
				coEvery { stickerRemoteDataSource.getAllTrending() }.throws(Exception("an error"))

				val result = stickerRepository.getAllTrending()
				result.collect {
						assert(it is com.yvkalume.gifapp.data.util.Result.Error)
				}

		}

		@OptIn(ExperimentalCoroutinesApi::class)
		@Test
		fun `successful request`() = runTest {
				coEvery { stickerRemoteDataSource.getAllTrending() } returns giphyHttpResponse

				val result = stickerRepository.getAllTrending()
				result.collect {
						assert(it is com.yvkalume.gifapp.data.util.Result.Success)
						assertEquals(it.data?.first()?.id, giphyHttpResponse.data.first().id)
				}
		}

		private val giphyHttpResponse = GiphyHttpResponse(
				data = listOf(
						GiphyItem(
								id = "gitphyitem1",
								title = "Lorem ipsum",
								images = GifImage(
										fixed_height = FixedHeight(
												url = "https://image.com/image.png"
										)
								)
						)
				),
				pagination = PaginationObject(
						offset = 0,
						total_count = 1,
						count = 1
				),
				meta = MetaObject(
						status = 200,
				)
		)
}