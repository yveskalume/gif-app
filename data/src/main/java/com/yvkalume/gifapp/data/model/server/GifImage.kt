package com.yvkalume.gifapp.data.model.server

@kotlinx.serialization.Serializable
data class GifImage(
    val fixed_height: FixedHeight
)

@kotlinx.serialization.Serializable
data class FixedHeight(
    val url: String = ""
)