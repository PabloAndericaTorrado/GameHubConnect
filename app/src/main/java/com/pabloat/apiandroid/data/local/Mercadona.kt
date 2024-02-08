package com.pabloat.apiandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val product_name: String,
    val iva: Int,
    val price: String,
    val categoriaId: Int
)

@Entity
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val products: List<Producto>
)


class ProductoListConverter {
    @TypeConverter
    fun fromString(value: String): List<Producto> {
        val listType = object : TypeToken<List<Producto>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Producto>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}