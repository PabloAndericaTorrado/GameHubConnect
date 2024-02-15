package com.pabloat.GameHubConnect.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pabloat.GameHubConnect.ui.util.VideojuegosItem
import com.pabloat.GameHubConnect.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun VideogameGenreScreen(mainViewModel: MainViewModel, genre: String) {
    val videojuegosList by mainViewModel.getVideojuegosByGenre(genre = genre).collectAsState(emptyList())
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(videojuegosList) { videojuego ->
                VideojuegosItem(videojuego)
            }
        }
    }
}
