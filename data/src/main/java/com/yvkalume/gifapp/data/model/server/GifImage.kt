package com.yvkalume.gifapp.data.model.server

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GifImage(
    @SerialName("fixed_height")
    val fixed_height: FixedHeight
)

@kotlinx.serialization.Serializable
data class FixedHeight(
    @SerialName("url")
    val url: String = ""
)