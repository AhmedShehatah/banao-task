package com.ahmed.banaotask.data.repositories

import android.util.Log
import com.ahmed.banaotask.data.models.Photo
import com.ahmed.banaotask.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotosRepository(private val apiService: ApiService) : IPhotosRepository {
    override fun getRecentPhotos(): Flow<List<Photo?>> = flow {

        try {
            val response = apiService.getRecentPhotos()
            emit(response.photos?.photo ?: emptyList())
        } catch (e: Exception) {
            Log.d("repo error catch", e.toString())
        }

    }
}