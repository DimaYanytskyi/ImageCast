package com.yanytskyi.dima.imagecast.app.di

import com.yanytskyi.dima.imagecast.data.firestore.FirestoreService
import com.yanytskyi.dima.imagecast.data.firestore.IFirestoreService
import com.yanytskyi.dima.imagecast.data.repository.IImageRepository
import com.yanytskyi.dima.imagecast.data.repository.ImageRepository
import com.yanytskyi.dima.imagecast.domain.interactor.IImageInteractor
import com.yanytskyi.dima.imagecast.domain.interactor.ImageInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    @Singleton
    fun bindFirestoreService(firestoreService: FirestoreService) : IFirestoreService

    @Binds
    @Singleton
    fun bindImageRepository(imageRepository: ImageRepository) : IImageRepository

    @Binds
    @Singleton
    fun bindImageInteractor(imageInteractor: ImageInteractor) : IImageInteractor
}