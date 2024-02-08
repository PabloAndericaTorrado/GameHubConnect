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
