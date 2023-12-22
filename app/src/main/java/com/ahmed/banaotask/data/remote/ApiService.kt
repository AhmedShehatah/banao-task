package com.ahmed.banaotask.data.remote

import com.ahmed.banaotask.data.models.RecentPhotosResponse
import com.ahmed.banaotask.utils.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.PHOTOS_LIST)
    suspend fun getRecentPhotos(): RecentPhotosResponse

}