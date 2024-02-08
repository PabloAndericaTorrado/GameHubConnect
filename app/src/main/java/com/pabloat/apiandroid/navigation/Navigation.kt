package com.pabloat.apiandroid.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabloat.apiandroid.ui.views.CategoriaScreen
import com.pabloat.apiandroid.ui.views.ProductoScreen
import com.pabloat.apiandroid.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNaviation(onNavController: NavHostController,mainViewmodel: MainViewModel) {
    NavHost(navController = onNavController, startDestination = Destinations.CategoriaScreen.route) {
        composable(Destinations.CategoriaScreen.route) {
            CategoriaScreen(mainViewmodel)
        }
        composable(Destinations.ProductoScreen.route) {
            ProductoScreen(mainViewmodel)
        }
    }
}