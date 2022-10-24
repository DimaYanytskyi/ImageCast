package com.yanytskyi.dima.imagecast.tools

import com.google.firebase.firestore.DocumentSnapshot
import com.yanytskyi.dima.imagecast.domain.model.Cast

fun DocumentSnapshot.toCast() : Cast {
    return Cast(
        this.data?.get("localImagePath").toString(),
        this.data?.get("firestoreImagePath").toString(),
        this.data?.get("imageEncoded").toString().decode()
    )
}