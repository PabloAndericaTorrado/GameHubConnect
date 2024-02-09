package com.pabloat.apiandroid.data.remote

class RemoteVideojuegoDataSource(private val apiService: ApiService) {
    suspend fun getVideojuegos() =
        apiService.getVideoJuegos()
}