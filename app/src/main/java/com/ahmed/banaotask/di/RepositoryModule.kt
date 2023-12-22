package com.ahmed.banaotask.di

import com.ahmed.banaotask.data.remote.ApiService
import com.ahmed.banaotask.data.repositories.IPhotosRepository
import com.ahmed.banaotask.data.repositories.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePhotosRepository(apiService: ApiService): IPhotosRepository {
        return PhotosRepository(apiService)
    }
}