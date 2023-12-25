@file:OptIn(ExperimentalGlideComposeApi::class)

package com.ahmed.banaotask.ui.features.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmed.banaotask.core.design.paging.ErrorMessage
import com.ahmed.banaotask.core.design.paging.LoadingNextPageItem
import com.ahmed.banaotask.core.design.paging.PageLoader
import com.ahmed.banaotask.ui.features.home.composables.PhotoItem
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {


    val recentPhotos = homeViewModel.recentPhotos.collectAsLazyPagingItems()


    LaunchedEffect(key1 = true) {
        homeViewModel.getRecentPhotos()

    }

    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        items(recentPhotos.itemCount) { index ->

            Card(
                Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(10.dp)
            ) {
                PhotoItem(url = recentPhotos[index]!!.urlS!!)
            }


        }

        recentPhotos.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        PageLoader(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = recentPhotos.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = recentPhotos.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.padding(4.dp)) }

    }
}




