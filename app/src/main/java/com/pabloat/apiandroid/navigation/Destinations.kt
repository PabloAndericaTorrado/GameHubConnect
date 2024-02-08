package com.pabloat.apiandroid.navigation

sealed class Destinations(val route: String) {
    object MainScreen : Destinations(route = "MainScreen")
}