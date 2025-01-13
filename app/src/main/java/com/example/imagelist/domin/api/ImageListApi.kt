package com.example.imagelist.domin.api

import com.example.imagelist.domin.data.CuratedImages
import com.example.imagelist.domin.data.Photo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageListApi {

    @GET("curated")
    fun getCuratedImageList(
        @Query("page") pageNumber: Int? = null,
        @Query("per_page") perPage: Int
    ) : Call<CuratedImages>

    @GET("photos/{photoId}")
    fun getImageWithId(
        @Path("photoId") photoId: Int,
    ) : Call<Photo>
}