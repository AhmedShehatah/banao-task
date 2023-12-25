package com.ahmed.banaotask.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmed.banaotask.data.models.Photo
import com.ahmed.banaotask.data.repositories.IPhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: IPhotosRepository) : ViewModel() {


    private val _recentPhotos: MutableStateFlow<PagingData<Photo>> =
        MutableStateFlow(PagingData.empty())

    val recentPhotos: StateFlow<PagingData<Photo>> = _recentPhotos

    fun getRecentPhotos() {
        viewModelScope.launch {
            repo.getRecentPhotos().distinctUntilChanged().cachedIn(viewModelScope).collect {
                _recentPhotos.value = it
            }
        }
    }
}