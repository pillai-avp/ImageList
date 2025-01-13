package com.example.imagelist.ui.view.screens.imageList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imagelist.domin.ImageRepository
import com.example.imagelist.domin.data.Photo

class ImagePagingSource(private val imageRepository: ImageRepository) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val nextPageNumber = params.key ?: 1
        val response =
            imageRepository.getCuratedImageList(
                pageNumber = nextPageNumber,
                perPage = params.loadSize
            )
        val prevKey = if(response.page== 1) null else response.page - 1
        val nextKey = response.page + 1
        return LoadResult.Page(
            data = response.photos,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}