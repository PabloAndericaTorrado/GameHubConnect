package com.pabloat.GameHubConnect.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.ui.util.GenreItem
import com.pabloat.GameHubConnect.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreScreen(mainViewModel: MainViewModel, onGenreSelected: (String) -> Unit, navHostController: NavHostController) {
    val genres by mainViewModel.generos.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(9f)) {
                items(genres) { genre ->
                    GenreItem(genre = genre) {
                        onGenreSelected(genre)
                    }
                }
            }
            VolverAtras(
                navHostController = navHostController,
                route = Destinations.InitScreen.route,
                text = "Volver al menú"
            )
        }
    }
}