package com.pabloat.apiandroid.data.local

import android.content.Context
import com.pabloat.apiandroid.data.local.AppDataBase
import com.pabloat.apiandroid.data.local.Weather
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