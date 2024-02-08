package com.pabloat.apiandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Producto::class, Categoria::class], version = 1)
@TypeConverters(ProductoListConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productoDao(): LocalProductoDao
    abstract fun categoriaDao(): LocalCategoriaDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, AppDataBase::class.java, "mercadona.sql")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
