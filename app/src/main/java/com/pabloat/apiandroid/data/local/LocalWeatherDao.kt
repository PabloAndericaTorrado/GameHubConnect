package com.antpaniagua.basicretrofit.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface LocalWeatherDao {

    @Query("SELECT * FROM Weather")
    fun getAll(): Flow<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(weathers: List<Weather>)

    @Update
    fun updateMovie(weather: Weather)

    @Delete
    fun deleteMovie(weather: Weather)
}