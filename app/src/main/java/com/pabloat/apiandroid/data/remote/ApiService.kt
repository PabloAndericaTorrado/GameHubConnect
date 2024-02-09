package com.pabloat.apiandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

        @GET("games")
        suspend fun getVideoJuegos(): List<VideoJuegoDTO>
    }


