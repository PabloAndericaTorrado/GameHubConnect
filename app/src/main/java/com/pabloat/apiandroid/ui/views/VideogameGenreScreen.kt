package com.pabloat.apiandroid.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pabloat.apiandroid.ui.util.VideojuegosItem
import com.pabloat.apiandroid.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideogameGenreScreen(mainViewModel: MainViewModel, genre: String) {
    val videojuegosList = mainViewModel.videojuegos.value.filter { it.genre == genre }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Videojuegos - $genre") }) },
        content = {
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
    )
}
