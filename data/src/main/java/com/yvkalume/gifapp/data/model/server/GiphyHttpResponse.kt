package com.yvkalume.gifapp.data.model.server

@kotlinx.serialization.Serializable
data class GiphyHttpResponse(
    val data: List<GiphyItem> = emptyList(),
    val pagination: PaginationObject,
    val meta: MetaObject
)
