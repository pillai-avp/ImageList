package com.example.imagelist.ui.view.screens.imageList

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.imagelist.domin.ImageRepository

class ImageListViewModel(
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val PAGE_SIZE = 15
    val imagePager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        ImagePagingSource(imageRepository)
    }.flow
}