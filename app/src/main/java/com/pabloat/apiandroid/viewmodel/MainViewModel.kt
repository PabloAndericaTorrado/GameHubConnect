package com.pabloat.apiandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabloat.apiandroid.data.local.Weather
import com.pabloat.apiandroid.ui.util.ScreenState
import com.pabloat.apiandroid.data.WeatherRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weathers: MutableStateFlow<List<Weather>> = MutableStateFlow(listOf())
    var weathers = _weathers.asStateFlow()

    //Estado inicial de ScreenState. Loading. Cuando la API informe de que ha terminado, se cambiará
    //en la lista donde se emplea
    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    //Este es un buen punto de ruptura para poder ver lo que está sucediendo en la petición
    //Añadir un valor a la condición (botón derecho Condition) del punto de ruptura para parar en una vuelta concreta
    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value =
            ScreenState.Error("Ha ocurrido un error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }

    /**
     * Consulta el tiempo remoto de la API con las coordenadas indicadas
     * Establecemos un par de valores como predeterminados ya que no solemos cambiarlos.
     *
     * Si algo va mal, handler capturará el evento y se pasará a mostrar el error
     */
    fun getRemoteWeather(
        latitude: Float,
        longitude: Float,
        daily: String = "temperature_2m_max,temperature_2m_min",
        timezone: String = "auto"
    ) {
        viewModelScope.launch(handler) {
            //delay(5000) //Quitar. Lo usamos para simular una pausa
            Log.d("VM", "Lanzamos petición a la API")
            val remoteWeathers: List<Weather> =
                repository.getRemoteWeather(latitude, longitude, daily, timezone)
            //Recogemos el resultado
            _weathers.value = remoteWeathers
            //Actualizamos el estado
            _uiState.value = ScreenState.Success(weathers)
            Log.d("VM", "Info obtenida: " + remoteWeathers.toString())
        }
        Log.d("VM", "Petición lanzada. Los datos irán llegando...")
    }
}