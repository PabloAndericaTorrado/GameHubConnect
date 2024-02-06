package com.antpaniagua.basicretrofit.data.remote

class RemoteWeatherDataSource(private val apiService: ApiService) {
    suspend fun getWeather(latitude: Float, longitude: Float, daily: String, timezone: String) =
        apiService.getWeather(latitude, longitude, daily, timezone)
}