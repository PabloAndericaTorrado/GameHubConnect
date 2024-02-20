package com.pabloat.GameHubConnect.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabloat.GameHubConnect.ui.views.AddRatingScreen
import com.pabloat.GameHubConnect.ui.views.AddScreen
import com.pabloat.GameHubConnect.ui.views.DeleteGameScreen
import com.pabloat.GameHubConnect.ui.views.DetailGameScreen
import com.pabloat.GameHubConnect.ui.views.EditScreen
import com.pabloat.GameHubConnect.ui.views.EditSearch
import com.pabloat.GameHubConnect.ui.views.GenreScreen
import com.pabloat.GameHubConnect.ui.views.InitScreen
import com.pabloat.GameHubConnect.ui.views.LoginScreen
import com.pabloat.GameHubConnect.ui.views.MainScreen
import com.pabloat.GameHubConnect.ui.views.ManageScreen
import com.pabloat.GameHubConnect.ui.views.VideogameGenreScreen
import com.pabloat.GameHubConnect.viewmodel.FireBaseViewModel
import com.pabloat.GameHubConnect.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNaviation(onNavController: NavHostController,mainViewmodel: MainViewModel,fireBaseViewModel: FireBaseViewModel) {
    NavHost(navController = onNavController, startDestination = Destinations.ManageScreen.route) {
        composable(Destinations.MainScreen.route) {
            MainScreen(mainViewmodel, navHostController = onNavController)
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
                VideogameGenreScreen(onNavController,mainViewModel = mainViewmodel, genre = genre)
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
        composable(Destinations.EditScreen.route) {
            EditScreen(onNavController = onNavController, mainViewModel = mainViewmodel)
        }
        composable(Destinations.LoginScreen.route) {
            LoginScreen(navController = onNavController, fireBaseViewModel)
        }
        composable(Destinations.DetailGameScreen.route) {
            DetailGameScreen(onNavController, mainViewmodel)
        }

        composable(Destinations.AddRatingScreen.route) {
            AddRatingScreen(onNavController, mainViewmodel, id)
        }


    }
}
