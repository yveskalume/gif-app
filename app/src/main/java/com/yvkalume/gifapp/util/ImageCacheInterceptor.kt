package com.yvkalume.gifapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.LruCache
import coil.decode.DataSource
import coil.intercept.Interceptor
import coil.request.ImageResult
import coil.request.SuccessResult

class ImageCacheInterceptor(
		private val context: Context,
		private val cache: LruCache<String, Drawable>
) : Interceptor {

		override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
				val value = cache.get(chain.request.data.toString())
				if (value != null) {
						return SuccessResult(
								drawable = value,
								request = chain.request,
								dataSource = DataSource.MEMORY_CACHE
						)
				}
				return chain.proceed(chain.request)
		}
}