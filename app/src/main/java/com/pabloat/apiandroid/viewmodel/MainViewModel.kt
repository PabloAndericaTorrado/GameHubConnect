package com.pabloat.apiandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabloat.apiandroid.data.MercadonaRepository

import com.pabloat.apiandroid.data.local.Videojuego
import com.pabloat.apiandroid.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MercadonaRepository) : ViewModel() {

    private val _videojuegos: MutableStateFlow<List<Videojuego>> = MutableStateFlow(listOf())
    var videojuegos = _videojuegos.asStateFlow()

    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value =
            ScreenState.Error("Ha ocurrido un error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }



    fun getRemoteCategoria(
       id:Int, name:String, productos: List<Videojuego>
    ) {
        viewModelScope.launch(handler) {
            //delay(5000) //Quitar. Lo usamos para simular una pausa
            Log.d("VM", "Lanzamos petición a la API")
            val remoteVideojuegos: List<Videojuego> =
                repository.getRemoteCategoria(id,name,productos)
            //Recogemos el resultado
            _videojuegos.value = remoteVideojuegos
            //Actualizamos el estado
            _uiState.value = ScreenState.Success(videojuegos)
            Log.d("VM", "Info obtenida: " + remoteVideojuegos.toString())
        }
        Log.d("VM", "Petición lanzada. Los datos irán llegando...")
    }
}
