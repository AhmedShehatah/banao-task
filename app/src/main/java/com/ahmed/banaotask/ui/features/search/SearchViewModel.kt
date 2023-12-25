@file:OptIn(FlowPreview::class)

package com.ahmed.banaotask.ui.features.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.banaotask.data.models.Photo
import com.ahmed.banaotask.data.repositories.IPhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: IPhotosRepository) : ViewModel() {


    private val _searchedPhotos = MutableStateFlow<List<Photo>>(emptyList())
    val searchedPhotos: StateFlow<List<Photo>> = _searchedPhotos
    private val searchChannel = Channel<String>()
    fun searchAboutPhotos(text: String) {
        viewModelScope.launch {

            searchChannel.send(text)
        }
    }

    val errorState = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            searchChannel.consumeAsFlow().debounce(1000).collect { text ->

                repository.getSearchedPhotos(text.ifBlank { "" }).debounce(1000).catch {
                    Log.d("error viewmodel", it.toString())
                    errorState.value = true
                }.collect {
                    _searchedPhotos.value = it
                }
            }
        }
    }

}