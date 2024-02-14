package com.pabloat.apiandroid.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabloat.apiandroid.ui.views.MainScreen
import com.pabloat.apiandroid.ui.views.VideogamesGenre
import com.pabloat.apiandroid.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNaviation(onNavController: NavHostController,mainViewmodel: MainViewModel) {
    NavHost(navController = onNavController, startDestination = Destinations.MainScreen.route) {
        composable(Destinations.MainScreen.route) {
            MainScreen(mainViewmodel)
        }
        composable(Destinations.ManageScreen.route) {
            ManageScreen(onNavController, mainViewmodel)
        }
        composable(Destinations.GenreScreen.route) {

        }
        composable(Destinations.VideoGamesGenre.route) { backStackEntry ->
            val genre = backStackEntry.arguments?.getString("genre")
            if (genre != null) {
                VideogamesGenre(onNavController, genre)
            }
        }

    }
}