package com.ahmed.banaotask.ui.features.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmed.banaotask.ui.features.home.composables.PhotoItem

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = hiltViewModel()) {

    val snackBarHostState = remember { SnackbarHostState() }
    val photos = searchViewModel.searchedPhotos.collectAsState().value

    var searchedText by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        searchViewModel.errorState.collect {
            if (it) {

                val result = snackBarHostState.showSnackbar(
                    message = "Network Error",
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        searchViewModel.searchAboutPhotos(searchedText)
                    }

                    SnackbarResult.Dismissed -> {

                    }
                }
                searchViewModel.errorState.value = false
            }

        }
    }
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }) { padding ->
        Column(Modifier.padding(padding)) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))

                    .padding(start = 16.dp, end = 8.dp),
                value = searchedText,
                onValueChange = {
                    searchedText = it

                    searchViewModel.searchAboutPhotos(it)
                },
                placeholder = { Text(text = "Search") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),


                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp),
                    )
                },
                trailingIcon = {
                    if (searchedText.isNotEmpty()) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { searchedText = "" }
                                .padding(12.dp)
                                .size(24.dp),
                        )
                    }
                },

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(photos.size) {
                    PhotoItem(url = photos[it].urlS!!)
                }
            }

        }
    }


}