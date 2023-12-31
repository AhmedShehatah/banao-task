package com.ahmed.banaotask.data.remote

import com.ahmed.banaotask.data.models.PhotosResponse
import com.ahmed.banaotask.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.PHOTOS_LIST)
    suspend fun getRecentPhotos(@Query("page") page: Int): PhotosResponse

    @GET(Constants.SEARCH)
    suspend fun getSearchedPhotos(@Query("text") text: String): PhotosResponse


}