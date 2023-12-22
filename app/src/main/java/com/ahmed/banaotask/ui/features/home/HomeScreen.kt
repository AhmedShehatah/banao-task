package com.ahmed.banaotask.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState
import com.bumptech.glide.integration.compose.placeholder

@ExperimentalGlideComposeApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {


    val recentPhotos = homeViewModel.recentPhotos.collectAsState().value


    LaunchedEffect(key1 = true) {
        homeViewModel.getRecentPhotos()

    }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(recentPhotos) { item ->
            Card(
                Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(20.dp),

                ) {
                GlideSubcomposition(model = item?.urlS,
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


        }

    }

}