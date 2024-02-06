package com.pabloat.apiandroid.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabloat.apiandroid.ui.ListScreen
import com.pabloat.apiandroid.ui.MainScreen
import com.pabloat.apiandroid.viewmodel.MainViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController, mainViewmodel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MainScreen.route,
    ) {
        composable(route = Destinations.MainScreen.route) {
            Destinations.MainScreen(
                mainViewmodel,
                onNavToLista = { navController.navigate(Destinations.ListScreen.route) })
        }

        composable(Destinations.ListScreen.route) {
            ListScreen(mainViewmodel, navController)
        }
    }
}

