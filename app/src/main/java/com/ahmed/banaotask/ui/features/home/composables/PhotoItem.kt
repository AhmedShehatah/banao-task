@file:OptIn(ExperimentalGlideComposeApi::class)

package com.ahmed.banaotask.ui.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState

@Composable
fun PhotoItem(url: String) {
    GlideSubcomposition(
        model = url,
        modifier = Modifier.fillMaxSize(),
        requestBuilderTransform = { it.thumbnail() }) {
        when (state) {
            RequestState.Failure -> Text(text = "Failed To Load Image")
            RequestState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}