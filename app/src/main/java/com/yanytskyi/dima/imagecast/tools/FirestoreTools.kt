package com.yanytskyi.dima.imagecast.tools

import android.content.Context
import com.google.firebase.firestore.DocumentSnapshot
import com.yanytskyi.dima.imagecast.domain.model.Image

fun DocumentSnapshot.toDomain() : Image {
    return Image(
        this.data?.get("localImagePath").toString(),
        this.data?.get("firestoreImagePath").toString(),
        this.data?.get("imageEncoded").toString().decode()
    )
}