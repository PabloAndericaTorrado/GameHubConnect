package com.pabloat.apiandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

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
    @Relation(
        parentColumn = "id",
        entityColumn = "categoriaId"
    )
    val products: List<Producto>
)
