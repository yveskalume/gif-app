package com.yvkalume.gifapp.data.model

data class GifImage(
		val fixed_height : FixedHeight
)

data class FixedHeight(
		val url: String = ""
)