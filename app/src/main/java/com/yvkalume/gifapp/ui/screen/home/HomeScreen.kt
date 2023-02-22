package com.yvkalume.gifapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.yvkalume.gifapp.R
import com.yvkalume.gifapp.ui.components.GifItem
import com.yvkalume.gifapp.ui.theme.GifAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
		val context = LocalContext.current
		val pages = context.resources.getStringArray(R.array.gif_categories)
		val pagerState = rememberPagerState()
		val coroutineScope = rememberCoroutineScope()

		Scaffold(
				topBar = {
						Column(
								modifier = Modifier
										.fillMaxWidth()
										.wrapContentHeight(),
								horizontalAlignment = Alignment.CenterHorizontally
						) {
								Text(
										text = stringResource(id = R.string.app_name),
										style = MaterialTheme.typography.h1,
										modifier = Modifier.padding(vertical = 8.dp)
								)

								ScrollableTabRow(
										backgroundColor = MaterialTheme.colors.surface,
										selectedTabIndex = pagerState.currentPage,
										edgePadding = 8.dp,
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
				HorizontalPager(state = pagerState, count = pages.size) {
						LazyColumn(
								modifier = Modifier
										.fillMaxSize()
										.padding(contentPadding),
								content = {
										items(10) {
												GifItem()
										}
								}
						)
				}
		}
}

@Preview
@Composable
fun HomeScreenPreview() {
		GifAppTheme {
				HomeScreen()
		}
}