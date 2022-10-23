package com.yanytskyi.dima.imagecast.data.repository

import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface IImageRepository {
    fun fetchImage() : Flow<ResultWrapper<Image>>
    suspend fun updateImage(image: Image)
}