package com.pabloat.apiandroid.navigation

sealed class Destinations(val route: String) {
    object MainScreen : Destinations(route = "main_screen")
    object ListScreen : Destinations(route = "list_screen")
}

