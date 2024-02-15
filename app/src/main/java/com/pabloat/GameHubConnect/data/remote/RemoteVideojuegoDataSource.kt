package com.pabloat.GameHubConnect.data.remote

class RemoteVideojuegoDataSource(private val apiService: ApiService) {
    suspend fun getVideojuegos() =
        apiService.getVideoJuegos()
}