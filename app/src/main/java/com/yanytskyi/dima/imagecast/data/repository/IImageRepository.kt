package com.yanytskyi.dima.imagecast.data.repository

import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.domain.model.Cast
import kotlinx.coroutines.flow.Flow

interface IImageRepository {
    fun fetchImage() : Flow<ResultWrapper<Cast>>
    suspend fun updateImage(cast: Cast)
}