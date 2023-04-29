package com.yvkalume.gifapp.data.model.server

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GiphyItem(
    @SerialName("id")
    val id: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("images")
    val images: GifImage
)
