package com.yvkalume.gifapp.ui.util

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

fun Context.downloadFile(link: String,fileName: String): Long {
    val request = DownloadManager.Request(Uri.parse(link))
    val params = DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
    request.setAllowedNetworkTypes(params)
        .setTitle("Gif app")
        .setDescription("Downloadin a gif")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
    val manager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    return manager.enqueue(request)
}