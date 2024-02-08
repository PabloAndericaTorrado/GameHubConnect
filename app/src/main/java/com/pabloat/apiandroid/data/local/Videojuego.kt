package com.pabloat.apiandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Videojuego(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String,
    val developer: String,
    val shortDescription: String,
    val genre: String,
    val platform: String,
    val date: String,
)
