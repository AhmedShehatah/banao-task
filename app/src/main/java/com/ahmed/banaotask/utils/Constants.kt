package com.ahmed.banaotask.utils

object Constants {
    const val BASE_URL: String = "https://api.flickr.com/services/rest/"

    const val PHOTOS_LIST: String =
        "?method=flickr.photos.getRecent&per_page=20&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"

    const val SEARCH =
        "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"
}