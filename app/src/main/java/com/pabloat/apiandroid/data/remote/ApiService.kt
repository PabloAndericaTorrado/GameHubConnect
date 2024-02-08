package com.pabloat.apiandroid.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("games")
    suspend fun getVideojuegos(
        id: Int,
        title: String,
        developer: String,
        shortDescription: String,
        genre: String,
        platform: String,
        date: String,
    ): VideoJuegosDTO
}
