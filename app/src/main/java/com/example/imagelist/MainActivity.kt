package com.example.imagelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.imagelist.ui.theme.ImageListTheme
import com.example.imagelist.ui.view.composable.ImageListScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageListTheme {
                ImageListScreens(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

