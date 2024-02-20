package com.pabloat.GameHubConnect.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabloat.GameHubConnect.data.VideojuegoRepository

import com.pabloat.GameHubConnect.data.local.Videojuego
import com.pabloat.GameHubConnect.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: VideojuegoRepository) : ViewModel() {

    private val _videojuegos: MutableStateFlow<List<Videojuego>> = MutableStateFlow(listOf())
    var videojuegos = _videojuegos.asStateFlow()

    private val _generos: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val generos: StateFlow<List<String>> = _generos


    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value =
            ScreenState.Error("Ha ocurrido un error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }

    private val _selectedVideojuegoId = MutableStateFlow<Int?>(null)
    val selectedVideojuegoId: StateFlow<Int?> = _selectedVideojuegoId

    init {
        viewModelScope.launch(handler) {
            repository.getAllGenres().collect { genres ->
                _generos.value = genres
            }
        }
    }

    fun setSelectedVideojuegoId(videojuegoId: Int?) {
        _selectedVideojuegoId.value = videojuegoId
    }

    fun getVideojuegosByGenre(genre: String): Flow<List<Videojuego>> {
        return repository.getVideojuegosByGenre(genre)
    }

    private val _selectedVideojuego = MutableStateFlow<Videojuego?>(null)
    val selectedVideojuego: StateFlow<Videojuego?> = _selectedVideojuego

    fun setSelectedVideojuego(videojuego: Videojuego?) {
        _selectedVideojuego.value = videojuego
    }

    fun getRemoteVideojuego(
    ) {
        viewModelScope.launch(handler) {
            delay(5000)
            Log.d("VM", "Lanzamos petición a la API")
            val remoteVideojuegos: List<Videojuego> =
                repository.getRemoteVideojuego()
            //Recogemos el resultado
            _videojuegos.value = remoteVideojuegos
            //Actualizamos el estado
            _uiState.value = ScreenState.Success(videojuegos)
            Log.d("VM", "Info obtenida: " + remoteVideojuegos.toString())
        }
        Log.d("VM", "Petición lanzada. Los datos irán llegando...")
    }

    fun addGame(videojuego: Videojuego) {
            viewModelScope.launch {
                Log.d("VM", "Añadimos el videojuego: $videojuego")
                repository.insertone(videojuego)
            }
    }

    suspend fun searchGame(gameTitle: String): Videojuego? {
        return withContext(Dispatchers.IO) {
            repository.searchVideojuegoByTitle(gameTitle)
        }
    }

    suspend fun deleteGame(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                repository.deleteVideojuego(id)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
    fun updateVideoGame(videojuego: Videojuego) {
        viewModelScope.launch {
            repository.updateVideojuego(videojuego)
        }
    }

    suspend fun getSelectedVideojuego(videojuegoId: Int): Videojuego? {
        if (videojuegoId != null) {
            return repository.getVideojuegoById(videojuegoId)
        }
        return null
    }

    fun updateVideojuegoRating(id: Int?, newRating: Float) {
        viewModelScope.launch {
            val videojuego = repository.getVideojuegoById(id)
            if (videojuego != null) {
                videojuego.valoracion = newRating
                repository.updateVideojuego(videojuego)
            }
        }
    }

    private val _videojuego: MutableStateFlow<Videojuego> =
        MutableStateFlow(Videojuego(0, "Default", "Default", "", "", "", "", "", 0.0f))
    var videojuego = _videojuego.asStateFlow()

    fun saveGame(videojuego: Videojuego) {
        _videojuego.value = videojuego
    }

    fun getGame(): Videojuego {
        return videojuego.value
    }
}