package com.pabloat.apiandroid.data.remote

import com.pabloat.apiandroid.data.local.Producto
import com.pabloat.apiandroid.data.remote.ApiService

class RemoteWeatherDataSource(private val apiService: ApiService) {
    suspend fun getCategoria(id: Int, name: String, products: Producto) =
        apiService.getCategoria(id, name, products)
}