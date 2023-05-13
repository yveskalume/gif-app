package com.yvkalume.gifapp.data.util

import com.yvkalume.gifapp.data.BuildConfig

object ServerEndpoints {

    private const val baseUrl = "https://api.giphy.com/v1"

    const val trendingGifs =
        "$baseUrl/stickers/trending?api_key=${BuildConfig.giphyApiKey}&limit=25"
}