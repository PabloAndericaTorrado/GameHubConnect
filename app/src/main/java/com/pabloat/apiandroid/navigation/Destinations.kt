package com.pabloat.apiandroid.navigation

sealed class Destinations(val route: String) {
    object MainScreen : Destinations(route = "MainScreen")
    object GenreScreen : Destinations(route = "GenreScreen")

    object VideoGamesGenre : Destinations(route = "VideogamesGenre/{genre}") {
        fun createRoute(genre: String): String {
            return "VideogamesGenre/$genre"
        }
    }
}