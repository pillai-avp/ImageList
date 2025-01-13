package com.example.imagelist.ui

import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.request.crossfade
import com.example.imagelist.ui.view.screens.image.ImageViewModel
import com.example.imagelist.ui.view.screens.imageList.ImageListViewModel
import com.example.imagelist.ui.view.screens.imageList.ImagePagingSource
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val imageListModule = module {
    viewModelOf(::ImageListViewModel)
    viewModelOf(::ImageViewModel)
}