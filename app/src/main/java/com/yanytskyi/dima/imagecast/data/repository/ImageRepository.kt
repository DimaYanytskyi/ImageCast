package com.yanytskyi.dima.imagecast.data.repository

import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.data.firestore.IFirestoreService
import com.yanytskyi.dima.imagecast.tools.toDomain
import com.yanytskyi.dima.imagecast.domain.model.Image
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val firestoreService: IFirestoreService
) : IImageRepository{
    override fun fetchImage() = flow {
        try {
            emit(ResultWrapper.Loading())
            val image = firestoreService.fetchDocument().toDomain()
            emit(ResultWrapper.Success(image))
        } catch (e: Exception) {
            emit(ResultWrapper.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun updateImage(image: Image){
        firestoreService.updateDocument(image)
    }
}