package com.pabloat.apiandroid.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("daily") daily: String,
        @Query("timezone") timezone: String
    ): WeatherDTO
}