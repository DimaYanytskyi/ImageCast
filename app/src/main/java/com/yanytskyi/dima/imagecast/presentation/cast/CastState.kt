package com.yanytskyi.dima.imagecast.presentation.cast

import android.graphics.Bitmap

data class CastState(
    val image: Bitmap? = null,
    val loading: Boolean = false,
    val message: String = ""
)
