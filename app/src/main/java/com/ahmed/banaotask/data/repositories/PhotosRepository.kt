package com.ahmed.banaotask.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmed.banaotask.data.models.Photo
import com.ahmed.banaotask.data.remote.ApiService
import com.ahmed.banaotask.data.repositories.paging.RecentPhotosPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotosRepository(private val apiService: ApiService) : IPhotosRepository {
    override fun getRecentPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                RecentPhotosPagingSource(apiService)
            }).flow
    }

    override fun getSearchedPhotos(text: String): Flow<List<Photo>> = flow {
        try {

            val response = apiService.getSearchedPhotos(text).photos
            val photos = response!!.photo!!.filterNotNull()
            emit(photos)
        } catch (e: Exception) {
            error("Error")
        }

    }
}