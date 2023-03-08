package com.yvkalume.gifapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yvkalume.gifapp.domain.entity.Sticker

@Composable
fun StickerItem(
		sticker: Sticker,
		modifier: Modifier = Modifier,
		onFavoriteClick: (Sticker) -> Unit
) {

		Column(modifier = Modifier.wrapContentHeight()) {
				CustomImageView(imageUrl = sticker.imageUrl, contentScale = ContentScale.FillHeight)
				Row(
						modifier = Modifier
								.fillMaxWidth()
								.padding(horizontal = 16.dp),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
				) {
						LikeIcon(
								isChecked = sticker.isFavorite,
								onClick = { onFavoriteClick(sticker) },
								modifier = Modifier.size(52.dp)
						)
						Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
				}
		}

		Divider(modifier = Modifier.padding(vertical = 16.dp))
}