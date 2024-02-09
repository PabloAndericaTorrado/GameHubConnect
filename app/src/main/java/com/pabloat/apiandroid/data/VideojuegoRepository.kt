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
    suspend fun getRemoteVideojuego(

    ): List<Videojuego> {
        val videojuegos = remoteds.getVideojuegos()
        val videojuegosLocales = ArrayList<Videojuego>()

        for (videojuego in videojuegos) {
            videojuegosLocales.add(videojuego.toVideojuego())
        }
        localds.insertVideojuego(videojuegosLocales)
        return videojuegosLocales
        //val listaVideojuegos = remoteds.getVideojuegos().toLocalList()
        //return listaVideojuegos
    }

    suspend fun getLocalVideojuego(): Flow<List<Videojuego>>{
        return localds.getAllVideojuego()
    }
}
