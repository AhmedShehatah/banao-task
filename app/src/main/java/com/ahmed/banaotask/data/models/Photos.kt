package com.ahmed.banaotask.data.models


import com.squareup.moshi.Json

data class Photos(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "pages")
    val pages: Int? = null,
    @Json(name = "perpage")
    val perpage: Int? = null,
    @Json(name = "photo")
    val photo: List<Photo?>? = null,
    @Json(name = "total")
    val total: Int? = null
)