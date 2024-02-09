package com.pabloat.apiandroid.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalVideojuegoDao {
    @Query("SELECT * FROM Videojuego")
    fun getAll(): Flow<List<Videojuego>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videojuegos: List<Videojuego>)

    @Update
    suspend fun update(videojuego: Videojuego)

    @Delete
    suspend fun delete(videojuego: Videojuego)
}