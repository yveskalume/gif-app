package com.yvkalume.gifapp.data.model.server

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MetaObject(
    @SerialName("msg")
    val msg: String = "",
    @SerialName("status")
    val status: Int,
    @SerialName("response_id")
    val response_id: String = ""
)
