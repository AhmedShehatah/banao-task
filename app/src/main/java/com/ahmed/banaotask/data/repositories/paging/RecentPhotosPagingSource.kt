package com.ahmed.banaotask.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmed.banaotask.data.models.Photo
import com.ahmed.banaotask.data.remote.ApiService

class RecentPhotosPagingSource(private val apiService: ApiService) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPage = params.key ?: 1
            val photos = apiService.getRecentPhotos(page = currentPage).photos
            val notNullPhotos = photos!!.photo!!.filterNotNull()

            LoadResult.Page(
                data = notNullPhotos,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (notNullPhotos.isEmpty()) null else photos.page!! + 1
            )
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }
}