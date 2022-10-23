package com.yanytskyi.dima.imagecast.data.firestore

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.SetOptions
import com.yanytskyi.dima.imagecast.domain.model.Image
import com.yanytskyi.dima.imagecast.tools.encode
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreService @Inject constructor(
    private val document: DocumentReference
) : IFirestoreService{

    override suspend fun fetchDocument(): DocumentSnapshot {
        return document.get().await()
    }

    override suspend fun updateDocument(image: Image){
        val editedDocumet = mutableMapOf<String, Any>()
        editedDocumet["localImagePath"] = image.localImagePath
        editedDocumet["firestoreImagePath"] = image.firestoreImagePath
        editedDocumet["imageEncoded"] = image.imageBitmap.encode()
        document.set(editedDocumet, SetOptions.merge()).await()
    }
}