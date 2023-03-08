package com.yvkalume.gifapp.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore

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