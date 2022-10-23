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
    this.compress(Bitmap.CompressFormat.JPEG, 0, baos)
    val b: ByteArray = baos.toByteArray()
    val encodedImage: String = Base64.encodeToString(b, Base64.DEFAULT)
    return encodedImage
}
