package com.pabloat.apiandroid.data.remote

import com.pabloat.apiandroid.data.local.Producto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categoria")
    suspend fun getCategoria(
        @Query("id") id: Int,
        @Query("name") name: String,
        @Query("products") products: Producto,
    ): CategoriaDTO

}