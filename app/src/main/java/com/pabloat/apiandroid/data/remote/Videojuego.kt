package com.pabloat.apiandroid.data.remote

import com.google.gson.annotations.SerializedName
import com.pabloat.apiandroid.data.local.Videojuego
import com.pabloat.apiandroid.data.local.Videojuego as LocalVideojuego


data class VideoJuegosDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("short_description")
    val description: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("developer")
    val developer: String,
    @SerializedName("release_date")
    val date: String
)

fun VideoJuegosDTO.toLocalList(): List<LocalVideojuego> {
    val tempList = mutableListOf<LocalVideojuego>()
    val nuevo = LocalVideojuego(
        id = id,
        title = title,
        developer = developer,
        shortDescription = description,
        genre = genre,
        platform = platform,
        date = date
    )
    tempList.add(nuevo)
    return tempList
}

fun VideoJuegosDTO.toLocalEntity(): LocalVideojuego {
    return LocalVideojuego(
        id = null,
        title = title,
        developer = developer,
        shortDescription = description,
        genre = genre,
        platform = platform,
        date = date
    )
}


