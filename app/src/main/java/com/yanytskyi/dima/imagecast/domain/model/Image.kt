package com.yanytskyi.dima.imagecast.domain.model

import android.graphics.Bitmap

data class Image(
    val localImagePath: String,
    val firestoreImagePath: String,
    val imageBitmap: Bitmap
)
