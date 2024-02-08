package com.pabloat.apiandroid.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pabloat.apiandroid.ui.views.CategoriaScreen
import com.pabloat.apiandroid.ui.views.ProductoScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNaviation(onNavController: NavHostController) {
    NavHost(navController = onNavController, startDestination = Destinations.CategoriaScreen.route) {
        composable(Destinations.CategoriaScreen.route) {
            CategoriaScreen()
        }
        composable(Destinations.ProductoScreen.route) {
            ProductoScreen()
        }
    }
}