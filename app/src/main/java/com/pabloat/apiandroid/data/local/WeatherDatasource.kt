package com.antpaniagua.basicretrofit.data.local

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

class WeatherDatasource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    suspend fun getAll(): Flow<List<Weather>> {
        return db.weatherDao().getAll().stateIn(GlobalScope)
    }

    fun insert(weather: Weather) {
        return db.weatherDao().insert(weather)
    }

    fun insertWeathers(weathers: List<Weather>) {
        db.weatherDao().inserts(weathers)
    }

    fun update(weather: Weather) {
        return db.weatherDao().updateMovie(weather)
    }

    fun delete(weather: Weather) {
        return db.weatherDao().deleteMovie(weather)
    }
}