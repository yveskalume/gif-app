package com.yvkalume.gifapp.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.yvkalume.gifapp.ui.util.shimmer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CustomImageView(
		modifier: Modifier = Modifier,
		imageUrl: String,
		contentScale: ContentScale = ContentScale.Crop,
		onImageLoaded: (Bitmap?) -> Unit = {}
) {
		val context = LocalContext.current
		val dispatcher = Dispatchers.IO.limitedParallelism(5)

		SubcomposeAsyncImage(
				modifier = Modifier
						.fillMaxWidth()
						.height(300.dp)
						.padding(16.dp)
						.clip(RoundedCornerShape(24.dp))
						.then(modifier),
				model = imageUrl,
				loading = {
						Box(
								modifier = Modifier
										.fillMaxSize()
										.shimmer()
						)
				},
				imageLoader = ImageLoader.Builder(context)
						.dispatcher(dispatcher)
						.components {
								if (Build.VERSION.SDK_INT >= 28) {
										add(ImageDecoderDecoder.Factory())
								} else {
										add(GifDecoder.Factory())
								}
						}
						.respectCacheHeaders(false)
						.build(),
				contentDescription = null,
				contentScale = contentScale,
				alignment = Alignment.Center,
				onSuccess = {
						val bitmap = it.result.drawable.toBitmap()
						onImageLoaded(bitmap)
				}
		)
}