package com.yvkalume.gifapp.data.model

data class GiphyResponse(
		val data: List<GifImage> = emptyList(),
		val pagination: PaginationObject,
		val meta: MetaObject
)
