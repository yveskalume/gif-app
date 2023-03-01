package com.yvkalume.gifapp.data.model.server

@kotlinx.serialization.Serializable
data class Gif(
		val id: String = "",
		val title: String = "",
		val images: GifImage
)
