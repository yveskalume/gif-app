package com.yvkalume.gifapp.util

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.google.modernstorage.permissions.StoragePermissions
import com.google.modernstorage.permissions.StoragePermissions.Action
import com.google.modernstorage.permissions.StoragePermissions.CreatedBy
import com.google.modernstorage.permissions.StoragePermissions.FileType

fun Context.downloadFile(link: String, fileName: String): Long {

	StoragePermissions.getPermissions(
		action = Action.READ_AND_WRITE,
		types = listOf(FileType.Image),
		createdBy = CreatedBy.AllApps
	)

	val request = DownloadManager.Request(Uri.parse(link))
	val params = DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
	request.setAllowedNetworkTypes(params)
		.setTitle("Gif app")
		.setDescription("Downloading a gif")
		.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
		.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
	val manager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

	return manager.enqueue(request)
}


fun Bitmap?.saveAndShare(context: Context, title: String) {
	if (this == null) {
		return
	}
	val bitmapPath: String = MediaStore.Images.Media.insertImage(
		context.contentResolver,
		this,
		title,
				null
		)
		val bitmapUri = Uri.parse(bitmapPath)
		val shareIntent = Intent(Intent.ACTION_SEND)
		shareIntent.type = "image/gif";
		shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
		context.startActivity(Intent.createChooser(shareIntent, title))
}