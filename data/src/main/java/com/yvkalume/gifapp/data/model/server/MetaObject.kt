package com.yvkalume.gifapp.data.model.server
@kotlinx.serialization.Serializable
data class MetaObject(
		val msg: String = "",
		val status: Int,
		//response_id
		val responseId: String = ""
)
