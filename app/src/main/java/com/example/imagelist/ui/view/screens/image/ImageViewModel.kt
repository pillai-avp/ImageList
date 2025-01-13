package com.example.imagelist.ui.view.screens.image

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagelist.domin.ImageRepository
import com.example.imagelist.domin.data.Photo
import kotlinx.coroutines.launch

class ImageViewModel(private val repository: ImageRepository) : ViewModel() {
    // Not the best way
    private val _state : MutableState<Photo?> = mutableStateOf(null)
    val state : State<Photo?> = _state

    fun setPhotoId(photoId: Int) {
        viewModelScope.launch{
            val photo = repository.getImageWithId(photoId)
            _state.value = photo
        }
    }
}