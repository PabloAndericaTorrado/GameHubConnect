package com.antpaniagua.boost.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antpaniagua.basicretrofit.ui.ListScreen
import com.antpaniagua.basicretrofit.ui.MainScreen
import com.antpaniagua.basicretrofit.viewmodel.MainViewModel


@Composable
fun Navigation(navController: NavHostController, mainViewmodel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MainScreen.route,
    ) {
        composable(route = Destinations.MainScreen.route) {
            MainScreen(
                mainViewmodel,
                onNavToLista = { navController.navigate(Destinations.ListScreen.route) })
        }

        composable(Destinations.ListScreen.route) {
            ListScreen(mainViewmodel, navController)
        }
    }
}

