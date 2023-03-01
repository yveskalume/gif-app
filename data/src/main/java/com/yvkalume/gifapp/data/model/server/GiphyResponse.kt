package com.yvkalume.gifapp.data.model.server

@kotlinx.serialization.Serializable
data class GiphyResponse(
		val data: List<Gif> = emptyList(),
		val pagination: PaginationObject,
		val meta: MetaObject
)
