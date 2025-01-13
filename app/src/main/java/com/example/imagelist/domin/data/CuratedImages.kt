package com.example.imagelist.domin.data


import com.example.imagelist.domin.data.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CuratedImages(
    @SerialName("next_page")
    val nextPage: String,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("photos")
    val photos: List<Photo>,
    @SerialName("total_results")
    val totalResults: Int
)