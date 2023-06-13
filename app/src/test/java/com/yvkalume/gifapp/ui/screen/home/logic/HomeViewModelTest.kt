package com.yvkalume.gifapp.ui.screen.home.logic

import app.cash.turbine.test
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.test.MavericksTestRule
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.domain.repository.GifRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mavericksRule = MavericksTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var initialState: HomeUiState

    @RelaxedMockK
    private lateinit var gifRepository: GifRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        initialState = HomeUiState(gifs = Uninitialized)
    }

    @Test
    fun `getData() should call refresh() and execute getAllTrending() with correct state`() =
        runTest {
            // Mock the response from getAllTrending()
            val gifList: List<Gif> = mockk()
            val successResult: Async<List<Gif>> = Success(gifList)

            coEvery { gifRepository.getAllTrending() } returns flowOf(gifList)

            coEvery { gifRepository.refresh() } just runs

//         getData() is called within the init block, when HomeViewModel is instanced
            viewModel = HomeViewModel(initialState, gifRepository)

//         Verify that the getAllTrending() function is called
            coVerify { gifRepository.getAllTrending() }

            viewModel.stateFlow.test {
                assertEquals(successResult, awaitItem().gifs)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `toggleFavorite() should call update() from gifRepository`() = runTest {
        val gif: Gif = spyk(
            Gif(
                "1",
                "name",
                "http://example.com",
                isFavorite = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )

//        coEvery { gifRepository.update(any()) } just runs

        viewModel = HomeViewModel(initialState, gifRepository)

        // Call the function under test
        viewModel.toggleFavorite(gif)

        // Verify that the update() function is called with the updated gif
        coVerify { gifRepository.update(any()) }
    }
}