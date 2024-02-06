package com.pabloat.apiandroid.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val products: Producto,
) {
    override fun toString(): String =
            "${name}: ${products}"
}