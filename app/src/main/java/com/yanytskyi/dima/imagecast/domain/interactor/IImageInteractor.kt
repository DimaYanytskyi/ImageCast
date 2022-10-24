package com.yanytskyi.dima.imagecast.domain.interactor

import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.domain.model.Cast
import kotlinx.coroutines.flow.Flow

interface IImageInteractor {
    fun fetchImage() : Flow<ResultWrapper<Cast>>
    suspend fun updateImage(cast: Cast)
}