package com.pabloat.apiandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

        @GET("games")
        suspend fun getVideoJuegos(
            @Query("id") id: Int,
            @Query("title") title: String,
            @Query("short_description") short_description: String,
            @Query("genre") genre: String,
            @Query("platform") platform: String,
            @Query("developer") developer: String,
            @Query("release_date") date: String
        ): VideoJuegosDTO
    }


