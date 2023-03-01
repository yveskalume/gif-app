package com.yvkalume.gifapp.data.util

import com.yvkalume.gifapp.data.BuildConfig

object ServerEndpoints {

		private const val baseUrl = "https://api.giphy.com/v1"

		const val trendingStickers = "$baseUrl/stickers/trending?api_key=${BuildConfig.giphyApiKey}&limit=25"
		const val trendingGifs = "$baseUrl/gifs/trending?api_key=${BuildConfig.giphyApiKey}&limit=25"
}