package com.yvkalume.gifapp.data.repository

import com.yvkalume.gifapp.data.datasource.sticker.StickerLocalDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.model.room.StickerEntity
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StickerRepositoryTest {

		@get:Rule
		val mockKRule = MockKRule(this)

		@MockK
		lateinit var stickerRemoteDataSource: StickerRemoteDataSource

		@MockK
		lateinit var stickerLocalDataSource: StickerLocalDataSource

		private lateinit var stickerRepository: StickerRepositoryImpl

		private val coroutineScope = CoroutineScope(SupervisorJob())

		@OptIn(ExperimentalCoroutinesApi::class)
		private val dispatcher = StandardTestDispatcher()

		@OptIn(ExperimentalCoroutinesApi::class)
		@Before
		fun setUp() {
				stickerRepository = StickerRepositoryImpl(
						stickerRemoteDataSource,
						stickerLocalDataSource,
						coroutineScope,
						dispatcher
				)
		}

		@OptIn(ExperimentalCoroutinesApi::class)
		@Test
		fun `should get Local data when an Exception is thrown by Remote datasource`() = runTest {
				coEvery { stickerRemoteDataSource.getAllTrending() }.throws(Exception("an error"))
				coEvery { stickerLocalDataSource.getAll() } returns flowOf(stickers)

				val result = stickerRepository.getAllTrending()
				result.collect {
						assert(it is com.yvkalume.gifapp.data.util.Result.Success)
						assert(it.data?.size == 2)
				}

		}

		private val stickers = listOf(
				StickerEntity(
						id = "giphyitem1",
						title = "Giphy Item 1",
						imageUrl = "https://imageurl.com/image.png"
				),
				StickerEntity(
						id = "giphyitem2",
						title = "Giphy Item 2",
						imageUrl = "https://imageurl.com/image.png"
				)
		)

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