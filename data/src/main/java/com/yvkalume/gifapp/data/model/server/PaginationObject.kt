package com.yvkalume.gifapp.data.model.server
@kotlinx.serialization.Serializable
data class PaginationObject(
		val offset: Int,
		val total_count: Int,
		val count: Int
)
