package com.yanytskyi.dima.imagecast.domain.interactor

import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.data.repository.IImageRepository
import com.yanytskyi.dima.imagecast.domain.model.Cast
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageInteractor @Inject constructor(
    private val imageRepository: IImageRepository
) : IImageInteractor{
    override fun fetchImage(): Flow<ResultWrapper<Cast>> {
        return imageRepository.fetchImage()
    }

    override suspend fun updateImage(cast: Cast){
        imageRepository.updateImage(cast)
    }

}