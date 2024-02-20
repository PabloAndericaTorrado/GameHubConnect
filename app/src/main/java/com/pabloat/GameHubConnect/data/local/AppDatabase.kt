package com.pabloat.GameHubConnect.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Videojuego::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun videojuegoDao(): LocalVideojuegoDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, AppDataBase::class.java, "videojuego.sql")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
