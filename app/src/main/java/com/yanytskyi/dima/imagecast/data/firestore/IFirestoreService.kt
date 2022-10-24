package com.yanytskyi.dima.imagecast.data.firestore

import com.google.firebase.firestore.DocumentSnapshot
import com.yanytskyi.dima.imagecast.domain.model.Cast

interface IFirestoreService {
    suspend fun fetchDocument() : DocumentSnapshot
    suspend fun updateDocument(cast: Cast)
}