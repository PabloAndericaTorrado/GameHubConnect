package com.pabloat.apiandroid.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: List<Int>?,
    val product_name: List<String>,
    val iva: List<Int>,
    val price: List<String>,
) {
    override fun toString(): String =
            "${product_name}: ${price}"
}