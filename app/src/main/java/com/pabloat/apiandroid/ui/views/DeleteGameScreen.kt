package com.pabloat.apiandroid.ui.views

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pabloat.apiandroid.data.local.Videojuego
import com.pabloat.apiandroid.ui.util.VideojuegosDeleteItem
import com.pabloat.apiandroid.ui.util.VideojuegosItem
import com.pabloat.apiandroid.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun DeleteGameScreen(onNavController: NavHostController, mainViewModel: MainViewModel) {
    val texto = remember { mutableStateOf("") }
    val videojuegoEncontrado = remember { mutableStateOf<Videojuego?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = texto.value,
            onValueChange = { texto.value = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            coroutineScope.launch {
                val videojuego = mainViewModel.searchGame(texto.value)
                videojuegoEncontrado.value = videojuego
            }
        }) {
            Text("Buscar juego")
        }

        videojuegoEncontrado.value?.let { videojuego ->
            VideojuegosDeleteItem(videojuego = videojuego, onDeleteClick = {
                coroutineScope.launch {
                    videojuego.id?.let { mainViewModel.deleteGame(it) }
                }
            })
        }
    }
}
