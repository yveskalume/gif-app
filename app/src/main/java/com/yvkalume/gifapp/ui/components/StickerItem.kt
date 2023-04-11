package com.yvkalume.gifapp.ui.components

import android.widget.Toast
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
import androidx.compose.material.icons.rounded.Download
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yvkalume.gifapp.domain.entity.Sticker
import com.yvkalume.gifapp.ui.util.downloadFile

@Composable
fun StickerItem(
    sticker: Sticker,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Sticker) -> Unit
) {

    val context = LocalContext.current

    Column(modifier = Modifier.wrapContentHeight()) {
        CustomImageView(
            modifier = modifier,
            imageUrl = sticker.imageUrl,
            contentScale = ContentScale.FillHeight,
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
                    Toast.makeText(context,"Download started", Toast.LENGTH_LONG).show()
                    context.downloadFile(sticker.imageUrl, sticker.title)
                }
            ) {
                Icon(imageVector = Icons.Rounded.Download, contentDescription = null)
            }
        }
    }

    Divider(modifier = Modifier.padding(vertical = 16.dp))
}