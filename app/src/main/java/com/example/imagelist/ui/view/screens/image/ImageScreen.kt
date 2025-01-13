package com.example.imagelist.ui.view.screens.image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.imagelist.ui.view.composable.ImageListAppTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImageScreen(modifier: Modifier = Modifier, photoID: Int, viewModel: ImageViewModel = koinViewModel()) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ImageListAppTopBar(text = "Image")
        }) { innerPadding ->
        LaunchedEffect(photoID) {
            viewModel.setPhotoId(photoID)
        }

        val state by viewModel.state

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AsyncImage(
                model = state?.src?.original,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}