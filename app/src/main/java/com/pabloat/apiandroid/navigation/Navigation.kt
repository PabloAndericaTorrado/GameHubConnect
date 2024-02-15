package com.pabloat.apiandroid.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabloat.apiandroid.ui.views.InitScreen
import com.pabloat.apiandroid.ui.views.GenreScreen
import com.pabloat.apiandroid.ui.views.AddScreen
import com.pabloat.apiandroid.ui.views.DeleteGameScreen
import com.pabloat.apiandroid.ui.views.EditScreen
import com.pabloat.apiandroid.ui.views.EditSearch
import com.pabloat.apiandroid.ui.views.MainScreen
import com.pabloat.apiandroid.ui.views.ManageScreen
import com.pabloat.apiandroid.ui.views.VideogameGenreScreen
import com.pabloat.apiandroid.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNaviation(onNavController: NavHostController,mainViewmodel: MainViewModel) {
    NavHost(navController = onNavController, startDestination = Destinations.InitScreen.route) {
        composable(Destinations.MainScreen.route) {
            MainScreen(mainViewmodel,navHostController = onNavController)
        }
        composable(Destinations.ManageScreen.route) {
            ManageScreen(navHostController = onNavController, mainViewModel = mainViewmodel)
        }
        composable(Destinations.GenreScreen.route) {
            GenreScreen(
                mainViewModel = mainViewmodel,
                onGenreSelected = { genre ->
                    onNavController.navigate(Destinations.VideoGamesGenre.createRoute(genre))
                },
                onNavController
            )
        }
        composable(Destinations.VideoGamesGenre.route) { backStackEntry ->
            val genre = backStackEntry.arguments?.getString("genre")
            if (genre != null) {
                VideogameGenreScreen(mainViewModel = mainViewmodel, genre = genre)
            }
        }
        composable(Destinations.InitScreen.route) {
            InitScreen(onNavController = onNavController)
        }
        composable(Destinations.AddScreen.route) {
            AddScreen(navHostController = onNavController, mainViewModel = mainViewmodel)
        }

        composable(Destinations.DeleteGameScreen.route) {
            DeleteGameScreen(onNavController = onNavController, mainViewModel = mainViewmodel)
        }

        composable(Destinations.EditSearch.route){
            EditSearch(onNavController = onNavController, mainViewModel = mainViewmodel)
        }
        composable(Destinations.EditScreen.route){
            EditScreen(onNavController = onNavController, mainViewModel = mainViewmodel)
        }
    }
}
