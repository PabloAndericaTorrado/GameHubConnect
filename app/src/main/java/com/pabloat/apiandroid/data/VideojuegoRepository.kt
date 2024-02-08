package com.pabloat.apiandroid.data


import com.pabloat.apiandroid.data.local.VideojuegoDatasource
import com.pabloat.apiandroid.data.local.Videojuego
import com.pabloat.apiandroid.data.remote.RemoteVideojuegoDataSource
import com.pabloat.apiandroid.data.remote.toLocalList
import kotlinx.coroutines.flow.Flow

class VideojuegoRepository(
    private val localds: VideojuegoDatasource,
    private val remoteds: RemoteVideojuegoDataSource
) {
    suspend fun getRemoteVideojuegos(
        id: Int,
        title: String,
        developer: String,
        shortDescription: String,
        genre: String,
        platform: String,
        date: String,
    ): List<Videojuego> {

        val videojuegosList = remoteds.getVideojuegos(id, title, developer, shortDescription, genre, platform, date).toLocalList()
        return videojuegosList
    }

    suspend fun getLocalVideojuego(): Flow<List<Videojuego>> {
        return localds.getAllVideojuego()
    }

    suspend fun insertLocalCategoria(Videojuego: Videojuego){
        localds.insertVideojuego(Videojuego)
    }


}
