package com.pabloat.apiandroid.data.local

import android.content.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

class VideojuegoDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)
    private val videojuegoDao: LocalVideojuegoDao = db.videojuegoDao()

    /**
     * PRODUCTO
     */

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun getAllVideojuego(): Flow<List<Videojuego>> {
        return videojuegoDao.getAll().stateIn(GlobalScope)
    }
    suspend fun insertVideojuego(videojuegos: List<Videojuego>) {
        videojuegoDao.insert(videojuegos)
    }
    suspend fun updateVideojuego(videojuego: Videojuego) {
        videojuegoDao.update(videojuego)
    }
    suspend fun deleteVideojuego(videojuego: Videojuego) {
        videojuegoDao.delete(videojuego)
    }

    fun getAllGenres(): Flow<List<String>> {
        return videojuegoDao.getAllGenres()
    }
    fun getVideojuegosByGenre(genre: String): Flow<List<Videojuego>> {
        return videojuegoDao.getVideojuegosByGenre(genre)
    }

    suspend fun insertoneVideojuego(videojuego: Videojuego) {
        videojuegoDao.insertone(videojuego)
    }

    suspend fun searchVideojuegoByTitle(title: String): Videojuego? {
        return videojuegoDao.getVideojuegoByTitle(title)
    }

    suspend fun deleteVideojuego(id: Int) {
        videojuegoDao.deleteVideojuego(id)
    }
}
