package com.pabloat.GameHubConnect.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.data.local.Videojuego
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.ui.util.VideojuegosDeleteItem
import com.pabloat.GameHubConnect.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteGameScreen(onNavController: NavHostController, mainViewModel: MainViewModel) {
    val texto = remember { mutableStateOf("") }
    val videojuegoEncontrado = remember { mutableStateOf<Videojuego?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val showSnackbar = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BuscarJuegoTextField(texto, mainViewModel, coroutineScope, videojuegoEncontrado)
        MostrarBotonEliminar(videojuegoEncontrado, mainViewModel, coroutineScope, showSnackbar)
        MostrarSnackbar(showSnackbar)
        VolverAtras(navHostController = onNavController, route = Destinations.ManageScreen.route, text = "Volver al menú")
    }
}


/**
 * MÉTODOS PARA LA PANTALLA DEL BORRADO
 */
@Composable
private fun BuscarJuegoTextField(
    texto: MutableState<String>,
    mainViewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    videojuegoEncontrado: MutableState<Videojuego?>
) {
    OutlinedTextField(
        value = texto.value,
        onValueChange = { texto.value = it },
        label = { Text("Título", color = Color.White) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.White)
    )

    Button(onClick = {
        coroutineScope.launch {
            val videojuego = mainViewModel.searchGame(texto.value)
            videojuegoEncontrado.value = videojuego
        }
    }) {
        Text("Buscar juego")
    }
}

@Composable
private fun MostrarBotonEliminar(
    videojuegoEncontrado: MutableState<Videojuego?>,
    mainViewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    showSnackbar: MutableState<Boolean>
) {
    videojuegoEncontrado.value?.let { videojuego ->
        VideojuegosDeleteItem(videojuego = videojuego, onDeleteClick = {
            coroutineScope.launch {
                videojuego.id?.let {
                    mainViewModel.deleteGame(it)
                    videojuegoEncontrado.value = null
                    showSnackbar.value = true
                }
            }
        })
    }
}

@Composable
private fun MostrarSnackbar(showSnackbar: MutableState<Boolean>) {
    if (showSnackbar.value) {
        Snackbar(
            action = {
                TextButton(onClick = { showSnackbar.value = false }) {
                    Text("Cerrar")
                }
            }
        ) {
            Text("El videojuego ha sido borrado")
        }
    }
}
