package com.yanytskyi.dima.imagecast.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

fun String.decode() : Bitmap {
    val decodedString: ByteArray = Base64.decode(this, Base64.DEFAULT)
    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    return decodedByte
}

fun Bitmap.encode() : String {
    val baos = ByteArrayOutputStream()
    val bitmap = getResizedBitmap(this, 500);
    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
    val b: ByteArray = baos.toByteArray()
    baos.close()
    val encodedImage: String = Base64.encodeToString(b, Base64.DEFAULT)
    return encodedImage
}

fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
    var width = image.width
    var height = image.height
    val bitmapRatio = width.toFloat() / height.toFloat()
    if (bitmapRatio > 1) {
        width = maxSize
        height = (width / bitmapRatio).toInt()
    } else {
        height = maxSize
        width = (height * bitmapRatio).toInt()
    }
    return Bitmap.createScaledBitmap(image, width, height, true)
}
