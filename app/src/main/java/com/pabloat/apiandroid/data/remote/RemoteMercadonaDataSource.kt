package com.antpaniagua.basicretrofit.data.remote

import com.pabloat.apiandroid.data.local.Producto

class RemoteMercadonaDataSource(private val apiService: ApiService) {
    suspend fun getCategorias(id: Int, name: String, products: List<Producto>) =
        apiService.getCategorias(id, name, products)
}