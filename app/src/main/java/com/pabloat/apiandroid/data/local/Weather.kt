package com.antpaniagua.basicretrofit.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val latitude: Float,
    val longitude: Float,
    @ColumnInfo(name = "temperature_max") val temperatureMax: String,
    @ColumnInfo(name = "temperature_min") val temperatureMin: String,
    val time: String,
) {
    override fun toString(): String =
        "${latitude},${longitude}: ${temperatureMax}º | ${temperatureMin}º ($time)"
}