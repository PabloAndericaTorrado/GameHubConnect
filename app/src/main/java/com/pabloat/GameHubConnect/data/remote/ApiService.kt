package com.pabloat.GameHubConnect.data.remote

import retrofit2.http.GET

interface ApiService {

        @GET("games")
        suspend fun getVideoJuegos(): List<VideoJuegoDTO>
    }


