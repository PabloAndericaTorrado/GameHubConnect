package com.pabloat.apiandroid.data.remote

import com.google.gson.annotations.SerializedName
import com.pabloat.apiandroid.data.local.Categoria
import com.pabloat.apiandroid.data.local.Producto

//WeatherDTO es el listado de datos (Data Transference Object) que recibiremos de la API. Sigue su formato
data class CategoriaDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("products")
    val products: Producto
)

//Forecasts, que viene de daily, es en este caso una lista de predicciones que nos suministra la API
data class ProductoDTO(
    @SerializedName("id")
    val id: List<Int>?,
    @SerializedName("display_name")
    val product_name: List<String>,
    @SerializedName("price_instructions")
   val precio: Precio
)

data class Precio(
    @SerializedName("iva")
    val iva: List<String>,
    @SerializedName("unit_price")
    val unit_price: List<String>,
)


// Convierte los datos de la categoría DTO en una entidad local
fun CategoriaDTO.toLocalEntity(): Categoria {
    val productList = mutableListOf<Producto>()
    for (i in products.id?.indices!!) {
        val precio = Precio(products.iva, products.price)
        val producto = Producto(products.id,products.product_name,products.iva,products.price)
        productList.add(producto)
    }
    return Categoria(id, name, productList)
}


