package com.pabloat.apiandroid.data.remote

data class CategoriaDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("products")
    val products: Producto
)
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

fun CategoriaDTO.toLocalEntity(): Categoria {
    val productList = mutableListOf<Producto>()
    for (i in products.id?.indices!!) {
        val precio = Precio(products.iva, products.price)
        val producto = Producto(products.id,products.product_name,products.iva,products.price)
        productList.add(producto)
    }
    return Categoria(id, name, productList)
}