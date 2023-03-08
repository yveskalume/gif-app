package com.yvkalume.gifapp.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.util.saveAndShare

@Composable
fun StickerItem(
		sticker: Sticker,
		modifier: Modifier = Modifier,
		onFavoriteClick: (Sticker) -> Unit
) {

		val context = LocalContext.current

		var imageDrawable by remember {
				mutableStateOf<Bitmap?>(null)
		}

		Column(modifier = Modifier.wrapContentHeight()) {
				CustomImageView(
						modifier = modifier,
						imageUrl = sticker.imageUrl,
						contentScale = ContentScale.FillHeight,
						onImageLoaded = {
								imageDrawable = it
						}
				)
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

						IconButton(
								onClick = {
										imageDrawable.saveAndShare(context, sticker.title)
								}
						) {
								Icon(imageVector = Icons.Rounded.Share, contentDescription = null)
						}
				}
		}

		Divider(modifier = Modifier.padding(vertical = 16.dp))
}