package com.pabloat.GameHubConnect.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.data.local.Videojuego
import com.pabloat.GameHubConnect.ui.util.VideojuegoDetailCard
import com.pabloat.GameHubConnect.viewmodel.MainViewModel

@Composable
fun DetailGameScreen(onNavController: NavHostController, mainViewModel: MainViewModel){
    val selectedVideojuegoId by mainViewModel.selectedVideojuegoId.collectAsState()
    val videojuegoState = remember(selectedVideojuegoId) {
        mutableStateOf<Videojuego?>(null)
    }
    LaunchedEffect(selectedVideojuegoId) {
        val videojuego = mainViewModel.getSelectedVideojuego(selectedVideojuegoId ?: -1)
        videojuegoState.value = videojuego
    }

    val videojuego = videojuegoState.value

    if (videojuego != null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            VideojuegoDetailCard(onNavController, videojuego = videojuego, mainViewModel)
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Cargando...")
        }
    }
}