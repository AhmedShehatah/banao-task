package com.ahmed.banaotask.data.repositories

import com.ahmed.banaotask.data.models.Photo
import kotlinx.coroutines.flow.Flow

interface IPhotosRepository {

    fun getRecentPhotos(): Flow<List<Photo?>>
}