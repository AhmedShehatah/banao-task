package com.ahmed.banaotask.data.repositories

import androidx.paging.PagingData
import com.ahmed.banaotask.data.models.Photo
import kotlinx.coroutines.flow.Flow

interface IPhotosRepository {

    fun getRecentPhotos(): Flow<PagingData<Photo>>

    fun getSearchedPhotos(text: String): Flow<List<Photo>>
}