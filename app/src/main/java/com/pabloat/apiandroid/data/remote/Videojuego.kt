package com.pabloat.apiandroid.data.remote

import com.google.gson.annotations.SerializedName
import com.pabloat.apiandroid.data.local.Videojuego



data class VideoJuegosDTO(
    @SerializedName("games")
    val data: List<VideoJuegoDTO>
)

data class VideoJuegoDTO(
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
) {
    fun toVideojuego(): Videojuego {
        return Videojuego(
            id = id,
            title = title,
            developer = developer,
            shortDescription = description,
            genre = genre,
            platform = platform,
            date = date
        )
    }
}

fun VideoJuegosDTO.toLocalList(): List<Videojuego> {
    val tempList = mutableListOf<Videojuego>()
    for (i in 0 .. data.size - 1){
        val nuevo = Videojuego(
           id = data.get(i).id,
            title = data.get(i).title,
            developer = data.get(i).developer,
            shortDescription = data.get(i).description,
            genre = data.get(i).genre,
            platform = data.get(i).platform,
            date = data.get(i).date

        )
        tempList.add(nuevo)
    }
    return tempList
}



