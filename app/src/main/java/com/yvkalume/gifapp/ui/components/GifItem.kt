package com.yvkalume.gifapp.ui.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.valentinilk.shimmer.shimmer
import com.yvkalume.gifapp.domain.entity.Gif

@Composable
fun GifItem(gif: Gif, modifier: Modifier = Modifier) {
		val context = LocalContext.current
		Column(modifier = Modifier.wrapContentHeight()) {
				SubcomposeAsyncImage(
						modifier = Modifier
								.fillMaxWidth()
								.height(300.dp)
								.padding(16.dp)
								.clip(RoundedCornerShape(24.dp))
								.then(modifier),
						model = gif.imageUrl,
						loading = {
								Box(
										modifier = Modifier
												.fillMaxSize()
												.shimmer()
								)
						},
						imageLoader = ImageLoader.Builder(context)
								.components {
										if (SDK_INT >= 28) {
												add(ImageDecoderDecoder.Factory())
										} else {
												add(GifDecoder.Factory())
										}
								}
								.build(),
						contentDescription = null,
						contentScale = ContentScale.Crop,
						alignment = Alignment.Center
				)
				Row(
						modifier = Modifier
								.fillMaxWidth()
								.padding(horizontal = 16.dp),
						horizontalArrangement = Arrangement.SpaceBetween
				) {
						Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
						Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
				}
		}

		Divider(modifier = Modifier.padding(vertical = 16.dp))
}