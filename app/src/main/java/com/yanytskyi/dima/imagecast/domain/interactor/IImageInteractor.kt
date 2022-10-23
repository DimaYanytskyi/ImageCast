package com.yanytskyi.dima.imagecast.domain.interactor

import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface IImageInteractor {
    fun fetchImage() : Flow<ResultWrapper<Image>>
    suspend fun updateImage(image: Image)
}