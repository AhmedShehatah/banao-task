package com.ahmed.banaotask.data.models


import com.squareup.moshi.Json

data class RecentPhotosResponse(
    @Json(name = "photos")
    val photos: Photos? = null,
    @Json(name = "stat")
    val stat: String? = null
)