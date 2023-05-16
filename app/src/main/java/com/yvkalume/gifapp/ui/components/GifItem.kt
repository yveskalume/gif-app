package com.yvkalume.gifapp.ui.components

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.yvkalume.gifapp.domain.entity.Gif
import com.yvkalume.gifapp.ui.util.downloadFile

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GifItem(gif: Gif, modifier: Modifier = Modifier, onFavoriteClick: (Gif) -> Unit) {

    val context = LocalContext.current

    val permissionState = rememberPermissionState(Manifest.permission.WRITE_EXTERNAL_STORAGE)


    Column(modifier = Modifier.wrapContentHeight()) {
        CustomImageView(
            modifier = modifier,
            imageUrl = gif.imageUrl,
            contentScale = ContentScale.FillWidth,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LikeIcon(
                isChecked = gif.isFavorite,
                onClick = { onFavoriteClick(gif) },
                modifier = Modifier.size(52.dp)
            )

            IconButton(
                onClick = {
                    Toast.makeText(context, "Download started", Toast.LENGTH_LONG).show()
                    permissionState.launchPermissionRequest()
                    if (permissionState.status.isGranted) {
                        context.downloadFile(gif.imageUrl, "${gif.title}.gif")
                    } else {
                        Toast.makeText(context, "Not Download permission", Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Icon(imageVector = Icons.Rounded.Download, contentDescription = null)
            }
        }
    }

    Divider(modifier = Modifier.padding(vertical = 16.dp))
}