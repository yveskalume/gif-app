package com.yvkalume.gifapp.data.model.server
@kotlinx.serialization.Serializable
data class PaginationObject(
		val offset: Int,
		// total_count
		val totalCount: Int,
		val count: Int
)
