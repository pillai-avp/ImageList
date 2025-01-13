package com.example.imagelist.ui.view.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.imagelist.ui.view.ImageListNavigation
import com.example.imagelist.ui.view.screens.image.ImageScreen
import com.example.imagelist.ui.view.screens.imageList.ImageListScreen

@Composable
fun ImageListScreens(modifier: Modifier) {
    val startDestination: String = ImageListNavigation.ImageList.screen
    val imageListMainNavHost = rememberNavController()
    NavHost(
        navController = imageListMainNavHost,
        modifier = modifier.fillMaxSize(),
        startDestination = startDestination
    ) {
        composable(ImageListNavigation.ImageList.screen) {
            ImageListScreen(modifier = Modifier.fillMaxSize(), navController = imageListMainNavHost)
        }

        composable(
            route = ImageListNavigation.Image.screen,
            arguments = listOf(navArgument("photoID") {
                defaultValue = 0
                type = NavType.IntType
            })
        ) {navBackStackEntry ->
            val photoID = navBackStackEntry.arguments?.getInt("photoID")
            photoID?.let {
                ImageScreen(modifier = Modifier.fillMaxSize(), photoID = it)
            }?: throw Exception("id parameter not received")
        }
    }
}