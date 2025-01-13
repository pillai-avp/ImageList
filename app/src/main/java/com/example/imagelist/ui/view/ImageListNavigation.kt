package com.example.imagelist.ui.view

sealed class ImageListNavigation(val screen : String) {
    object ImageList : ImageListNavigation("ImageList")
    object Image : ImageListNavigation("Image/{photoID}"){
        fun passParameter(id : Int) : String {
            return screen.replace(oldValue = "{photoID}", newValue = id.toString())
        }
    }
}