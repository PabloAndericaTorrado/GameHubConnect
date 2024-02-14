package com.pabloat.apiandroid.navigation

sealed class Destinations(val route: String) {
    object MainScreen : Destinations(route = "MainScreen")
    object ManageScreen : Destinations(route = "ManageScreen")

    object GenreScreen : Destinations(route = "GenreScreen")

    object VideoGamesGenre : Destinations(route = "VideogamesGenreScreen/{genre}") {
        fun createRoute(genre: String): String {
            return "VideogamesGenreScreen/$genre"
        }
    }

    object AddScreen : Destinations(route = "AddScreen")


    object  InitScreen : Destinations(route = "InitScreen")
}