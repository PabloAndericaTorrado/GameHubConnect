package com.pabloat.apiandroid.data.remote

class RemoteVideojuegoDataSource(private val apiService: ApiService) {
    suspend fun getVideojuegos(id: Int, title: String, developer: String, shortDescription: String, genre: String, platform: String, date: String) =
        apiService.getVideoJuegos(id, title, developer, shortDescription, genre, platform, date)
}