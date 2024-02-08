package com.pabloat.apiandroid.data.remote

import com.pabloat.apiandroid.data.local.Categoria
import com.pabloat.apiandroid.data.local.Producto
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategorias(id: Int, name: String, products: List<Producto>): List<Categoria>

    @GET("products")
    suspend fun getProductos(): List<Producto>
}
