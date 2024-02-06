package com.pabloat.apiandroid.data.remote

import com.pabloat.apiandroid.data.remote.ApiService

class RemoteWeatherDataSource(private val apiService: ApiService) {
    suspend fun getWeather(latitude: Float, longitude: Float, daily: String, timezone: String) =
        apiService.getWeather(latitude, longitude, daily, timezone)
}