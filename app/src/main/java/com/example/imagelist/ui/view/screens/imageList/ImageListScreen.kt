package com.example.imagelist.ui.view.screens.imageList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.example.imagelist.ui.theme.ImageListTheme
import com.example.imagelist.ui.theme.TransparentPink40
import com.example.imagelist.ui.view.ImageListNavigation
import com.example.imagelist.ui.view.composable.ImageListAppTopBar
import org.koin.androidx.compose.koinViewModel

val list = (0..50).toList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ImageListViewModel = koinViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ImageListAppTopBar(text = "Image list")
        }) { innerPadding ->
        val pagedImages = viewModel.imagePager.collectAsLazyPagingItems()
        when (pagedImages.loadState.refresh) {
            is LoadState.Error -> {
                Log.d("", "At error screen")
            }

            is LoadState.Loading -> {
                Log.d("", "At  Loading")
            }

            is LoadState.NotLoading -> {
                Log.d("", "At  Not Loading")
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(
                        count = pagedImages.itemCount,
                        key = pagedImages.itemKey {
                            it.id + pagedImages.itemSnapshotList.items.indexOf(
                                it
                            )
                        },
                        contentType = pagedImages.itemContentType { "image-list-item-type" }) { index ->
                        val item = pagedImages[index]
                        item?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            ImageListItem(
                                url = it.src.medium,
                                modifier = Modifier,
                                onClick = { id  ->
                                    navController.navigate(ImageListNavigation.Image.passParameter(id))
                                },
                                id = it.id,
                                info = it.alt
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }

    }

}

@Composable
private fun ImageListItem(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    url: String,
    id: Int,
    info: String
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable() {
            onClick(id)
        }) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "$id",
            modifier = modifier
                .align(alignment = Alignment.BottomCenter)
                .background(color = TransparentPink40)
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ImageListTheme {
        ImageListItem(onClick = {}, url = ".", id = 1, info = "")
    }
}