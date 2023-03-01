package com.yvkalume.gifapp.data.model.server
@kotlinx.serialization.Serializable
data class MetaObject(
		val msg: String = "",
		val status: Int,
		val response_id: String = ""
)
