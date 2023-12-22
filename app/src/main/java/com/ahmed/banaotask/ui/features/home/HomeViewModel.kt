package com.ahmed.banaotask.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.banaotask.data.models.Photo
import com.ahmed.banaotask.data.repositories.IPhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: IPhotosRepository) : ViewModel() {


    private val _recentPhotos: MutableStateFlow<List<Photo?>> = MutableStateFlow(emptyList())

    val recentPhotos: StateFlow<List<Photo?>> = _recentPhotos

    fun getRecentPhotos() {
        viewModelScope.launch {
            repo.getRecentPhotos().collect {
                _recentPhotos.value = it
            }
        }
    }
}