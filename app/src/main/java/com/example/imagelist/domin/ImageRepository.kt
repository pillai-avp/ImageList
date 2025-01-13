package com.example.imagelist.domin

import com.example.imagelist.domin.api.ImageListApi
import com.example.imagelist.domin.data.CuratedImages
import com.example.imagelist.domin.data.Photo
import retrofit2.awaitResponse

interface ImageRepository {
    suspend fun getCuratedImageList(pageNumber: Int? = null, perPage: Int): CuratedImages
    suspend fun getImageWithId(id: Int) : Photo
}

class ImageDataSource(private val imageListApi: ImageListApi) : ImageRepository {
    override suspend fun getCuratedImageList(pageNumber: Int?, perPage: Int): CuratedImages {
        return imageListApi.getCuratedImageList(pageNumber = pageNumber, perPage = perPage).awaitResponse().body()?: throw Exception("No data exist : getCuratedImageList")
    }

    override suspend fun getImageWithId(id: Int): Photo {
        return imageListApi.getImageWithId(id).awaitResponse().body()?:throw Exception("No data exist : getImageWithId")
    }

}