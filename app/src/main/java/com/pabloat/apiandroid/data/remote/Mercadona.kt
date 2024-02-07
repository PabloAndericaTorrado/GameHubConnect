package com.pabloat.apiandroid.data.remote

import com.google.gson.annotations.SerializedName
import com.pabloat.apiandroid.data.local.Categoria
import com.pabloat.apiandroid.data.local.Producto

data class CategoriaDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("products")
    val products: List<ProductoDTO>
)

data class ProductoDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("display_name")
    val product_name: String,
    @SerializedName("price_instructions")
    val precio: PrecioDTO
)

data class PrecioDTO(
    @SerializedName("iva")
    val iva: Int,
    @SerializedName("unit_price")
    val unit_price: String
)

fun CategoriaDTO.toLocalEntity(): Categoria {
    val productList = products.map { it.toLocalEntity(id) }
    return Categoria(id, name, productList)
}

fun ProductoDTO.toLocalEntity(categoriaId: Int): Producto {
    return Producto(id, product_name, precio.iva, precio.unit_price, categoriaId)
}
