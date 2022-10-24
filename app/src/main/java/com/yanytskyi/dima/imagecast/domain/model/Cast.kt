package com.yanytskyi.dima.imagecast.domain.model

import android.graphics.Bitmap

data class Cast(
    val localImagePath: String,
    val firestoreImagePath: String,
    val imageBitmap: Bitmap
)
