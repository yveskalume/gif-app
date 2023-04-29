package com.yvkalume.gifapp.data.model.server

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GiphyHttpResponse(
    @SerialName("data")
    val data: List<GiphyItem> = emptyList(),
    @SerialName("pagination")
    val pagination: PaginationObject,
    @SerialName("meta")
    val meta: MetaObject
)
