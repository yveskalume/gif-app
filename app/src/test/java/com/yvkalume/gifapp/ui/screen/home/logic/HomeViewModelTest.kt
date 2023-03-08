package com.yvkalume.gifapp.ui.screen.home.logic

import com.yvkalume.gifapp.data.model.mapper.GifMapper
import com.yvkalume.gifapp.data.model.room.GifEntity
import com.yvkalume.gifapp.data.repository.GifRepositoryImpl
import com.yvkalume.gifapp.data.repository.StickerRepositoryImpl
import com.yvkalume.gifapp.data.util.Result
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

		@get:Rule
		val mockKRule = MockKRule(this)

		private lateinit var homeViewModel: HomeViewModel

		@RelaxedMockK
		lateinit var gifRepository: GifRepositoryImpl

		@RelaxedMockK
		lateinit var stickerRepository: StickerRepositoryImpl

		@Before
		fun setUp() {
				homeViewModel = HomeViewModel(gifRepository, stickerRepository)
		}

		// fixme : should be fixed
		@OptIn(ExperimentalCoroutinesApi::class)
		@Test
		fun `gifs state is success when result is success`() = runTest {
				val collectJob = launch(UnconfinedTestDispatcher()) { homeViewModel.gifs.collect() }
				every { gifRepository.getAllTrending() } returns  flowOf(GifMapper.mapList(gifs))

				gifRepository.getAllTrending()

				val state = homeViewModel.gifs.value

				assert(state is HomeUiState.Success<*>)

				collectJob.cancel()
		}


		val gifs = listOf(
				GifEntity(
						id = "1",
						title = "Lorem ipsum",
						imageUrl = "https://image.com/image.png",
						createdAt = System.currentTimeMillis(),
						updatedAt = System.currentTimeMillis()
				)
		)

		val stickers = listOf(
				GifEntity(
						id = "1",
						title = "Lorem ipsum",
						imageUrl = "https://image.com/image.png",
						createdAt = System.currentTimeMillis(),
						updatedAt = System.currentTimeMillis()
				)
		)

}