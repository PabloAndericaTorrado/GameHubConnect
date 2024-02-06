package com.antpaniagua.retrofit.data

import com.antpaniagua.basicretrofit.data.local.Weather
import com.antpaniagua.basicretrofit.data.local.WeatherDatasource
import com.antpaniagua.basicretrofit.data.remote.RemoteWeatherDataSource
import com.antpaniagua.basicretrofit.data.remote.toLocalList
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio de Weathers
 *
 * Recibe los dos datasources (o repositorios) local y remoto e interactua con ellos
 *
 */
class WeatherRepository(
    private val localds: WeatherDatasource,
    private val remoteds: RemoteWeatherDataSource
) {
    suspend fun getRemoteWeather(
        latitude: Float, longitude: Float, daily: String, timezone: String
    ): List<Weather> {
        //Tomamos el resultado y lo convertimos en una entidad local
        val forecasts: Weather = Weather(0, 0.0f, 0.0f, "", "", "")

        //Primera prueba, compartimos datos mezclados
        //val forecasts = remoteds.getWeather(latitude, longitude, daily, timezone).toLocalEntity()
        //Log.d("REPO", forecasts.toString())
        //return listOf(forecasts)

        val forecastsList = remoteds.getWeather(latitude, longitude, daily, timezone).toLocalList()
        return forecastsList
    }

    suspend fun getLocalWeather(): Flow<List<Weather>> {
        return localds.getAll()
    }

    suspend fun insertLocal(weather: Weather) {
        localds.insert(weather)
    }
}