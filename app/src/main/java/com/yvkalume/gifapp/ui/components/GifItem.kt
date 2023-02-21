package com.yvkalume.gifapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.yvkalume.gifapp.ui.theme.GifAppTheme

@Composable
fun GifItem() {
		Column(modifier = Modifier.wrapContentHeight()) {
				SubcomposeAsyncImage(
						modifier = Modifier
								.fillMaxWidth()
								.height(200.dp),
						model = "https://example.com/image.jpg",
						loading = {
								CircularProgressIndicator()
						},
						contentDescription = null
				)
				Row(
						modifier = Modifier
								.fillMaxWidth()
								.padding(8.dp),
						horizontalArrangement = Arrangement.SpaceBetween
				) {
						Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
						Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
				}
		}
}

@Preview
@Composable
fun GiftItemPreview() {
		GifAppTheme {
				GifItem()
		}
}