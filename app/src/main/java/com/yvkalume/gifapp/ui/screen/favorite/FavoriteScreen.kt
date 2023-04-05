package com.yvkalume.gifapp.ui.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.yvkalume.gifapp.R
import com.yvkalume.gifapp.ui.components.GifListView
import com.yvkalume.gifapp.ui.components.StickerListView
import com.yvkalume.gifapp.ui.screen.favorite.logic.FavoriteUiState
import com.yvkalume.gifapp.ui.screen.favorite.logic.FavoriteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FavoriteScreen(homeViewModel: FavoriteViewModel = mavericksViewModel()) {
    val context = LocalContext.current
    val pages = context.resources.getStringArray(R.array.gif_categories)
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val gifsState by homeViewModel.collectAsState(FavoriteUiState::gifs)
    val stickersState by homeViewModel.collectAsState(FavoriteUiState::stickers)

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.favorites),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.padding(top = 16.dp)
                )

                TabRow(
                    backgroundColor = MaterialTheme.colors.surface,
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    }
                ) {
                    // Add tabs for all of our pages
                    pages.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        HorizontalPager(
            state = pagerState,
            count = pages.size,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(contentPadding)
        ) {
            when (pagerState.currentPage) {
                0 -> {
                    StickerListView(
                        modifier = Modifier.fillMaxSize(),
                        stickersState = { stickersState },
                        onFavoriteClick = {
                            homeViewModel.removerFavorite(it)
                        }
                    )
                }
                1 -> {
                    GifListView(
                        modifier = Modifier.fillMaxSize(),
                        gifsState = { gifsState },
                        onFavoriteClick = {
                            homeViewModel.removeFavorite(it)
                        }
                    )
                }
            }
        }
    }
}