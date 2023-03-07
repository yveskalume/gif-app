package com.yvkalume.gifapp.domain.entity

data class Gif(
		val id: String,
		val title: String,
		val imageUrl: String,
		val isFavorite: Boolean
)
