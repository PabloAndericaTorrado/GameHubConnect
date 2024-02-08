package com.pabloat.apiandroid.data.remote

import com.pabloat.apiandroid.data.local.Producto
import com.pabloat.apiandroid.data.remote.ApiService

class RemoteMercadonaDataSource(private val apiService: ApiService) {
    suspend fun getCategorias(id: Int, name: String, products: List<Producto>) =
        apiService.getCategorias(id, name, products)
}