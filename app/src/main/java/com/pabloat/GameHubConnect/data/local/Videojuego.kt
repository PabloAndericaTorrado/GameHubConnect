package com.pabloat.GameHubConnect.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Videojuego(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String,
    val thumbnail: String,
    val developer: String,
    val shortDescription: String,
    val genre: String,
    val platform: String,
    val date: String,
    var valoracion: Float // nuevo atributo
){
    override fun toString(): String {
        return super.toString()
    }
}
