package com.pabloat.apiandroid.data.local

import android.content.Context
import kotlinx.coroutines.flow.Flow

class VideojuegoDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)
    private val videojuegoDao: LocalVideojuegoDao = db.videojuegoDao()

    /**
     * PRODUCTO
     */

    suspend fun getAllVideojuego(): Flow<List<Videojuego>> {
        return videojuegoDao.getAll()
    }
    suspend fun insertVideojuego(videojuego: Videojuego) {
        videojuegoDao.insert(videojuego)
    }
    suspend fun updateVideojuego(videojuego: Videojuego) {
        videojuegoDao.update(videojuego)
    }
    suspend fun deleteVideojuego(videojuego: Videojuego) {
        videojuegoDao.delete(videojuego)
    }
}
