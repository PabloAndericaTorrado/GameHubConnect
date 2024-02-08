package com.pabloat.apiandroid.navigation

sealed class Destinations(val route: String) {
    object CategoriaScreen : Destinations(route = "categoria_screen")
    object ProductoScreen : Destinations(route = "producto_screen")
}