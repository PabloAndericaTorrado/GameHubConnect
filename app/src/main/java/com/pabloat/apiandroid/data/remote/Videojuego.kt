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

fun List<VideoJuegosDTO>.toLocalList(): List<Videojuego> {
    val tempList = mutableListOf<Videojuego>()
    for (videojuegoDTO in this) {
        val nuevo = LocalVideojuego(
            id = null,
            title = videojuegoDTO.title,
            developer = videojuegoDTO.developer,
            shortDescription = videojuegoDTO.description,
            genre = videojuegoDTO.genre,
            platform = videojuegoDTO.platform,
            date = videojuegoDTO.date
        )
        tempList.add(nuevo)
    }
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


