package com.pabloat.apiandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pabloat.apiandroid.data.remote.ProductoDTO

@Entity
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val products: MutableList<Producto>,
) {
    override fun toString(): String =
            "${name}: ${products}"
}