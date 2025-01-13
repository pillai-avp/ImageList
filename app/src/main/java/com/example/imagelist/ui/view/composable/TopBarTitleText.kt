package com.example.imagelist.ui.view.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ImageListAppTopBar(text: String) {
    Surface(shadowElevation = 2.dp) {
        TopAppBar(title = {
            TopBarTitleText(text = text)
        })
    }
}

@Composable
fun TopBarTitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun TopBarTitleTextPreview (modifier: Modifier = Modifier) {
    ImageListAppTopBar(text = "Image list")
}