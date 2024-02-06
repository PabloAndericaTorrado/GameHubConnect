package com.antpaniagua.basicretrofit.data.remote

import com.antpaniagua.basicretrofit.data.local.Weather
import com.google.gson.annotations.SerializedName
import com.antpaniagua.basicretrofit.data.local.Weather as LocalWeather

//WeatherDTO es el listado de datos (Data Transference Object) que recibiremos de la API. Sigue su formato
data class WeatherDTO(
    @SerializedName("latitude")
    val latitude: Float,
    @SerializedName("longitude")
    val longitude: Float,
    @SerializedName("generationtime_ms")
    val generationtime_ms: Double,
    @SerializedName("utc_offset_seconds")
    val utc_offset_seconds: Int,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezone_abbreviation: String,
    val elevation: Double,
    @SerializedName("daily")
    val daily: Forecasts
)

//Forecasts, que viene de daily, es en este caso una lista de predicciones que nos suministra la API
data class Forecasts(
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>
)


//loLocalEntity convierte las lecturas que hemos realizado a objetos locales
fun WeatherDTO.toLocalEntity() = LocalWeather(
        id = null,
        latitude = latitude,
        longitude = longitude,
        temperatureMax = daily.temperatureMax.toString(),
        temperatureMin = daily.temperatureMin.toString(),
        time = "Tiempos" + daily.time.toString()
    )

//toLocalList convierte los datos en una lista de objetos Weather, más adecuado en este caso
fun WeatherDTO.toLocalList(): List<Weather> {
    val tempList = mutableListOf<LocalWeather>()
    //Ojo con meterse en un bucle infinto. Mejorar esto
    for (i in (0..daily.time.size - 1)) {
        val nuevo = LocalWeather(
            id = null,
            latitude = latitude,
            longitude = longitude,
            temperatureMax = daily.temperatureMax.get(i).toString(),
            temperatureMin = daily.temperatureMin.get(i).toString(),
            time = daily.time.get(i),
        )
        tempList.add(nuevo)
    }
    return tempList
}


/**
 * Ejemplo de entrada de datos de la API
 *
 *
 *
 * latitude	38.88
 * longitude	-6.9700003
 * generationtime_ms	0.051975250244140625
 * utc_offset_seconds	3600
 * timezone	"Europe/Madrid"
 * timezone_abbreviation	"CET"
 * elevation	195
 * daily_units
 * time	"iso8601"
 * temperature_2m_max	"°C"
 * temperature_2m_min	"°C"
 * daily
 * time
 * 0	"2024-02-04"
 * 1	"2024-02-05"
 * 2	"2024-02-06"
 * 3	"2024-02-07"
 * 4	"2024-02-08"
 * 5	"2024-02-09"
 * 6	"2024-02-10"
 * temperature_2m_max
 * 0	18.2
 * 1	17.2
 * 2	15.6
 * 3	14.8
 * 4	15.9
 * 5	15.3
 * 6	12.9
 * temperature_2m_min
 * 0	4.6
 * 1	4.9
 * 2	8.4
 * 3	9.9
 * 4	9.3
 * 5	11.6
 * 6	8.6
 *
 */