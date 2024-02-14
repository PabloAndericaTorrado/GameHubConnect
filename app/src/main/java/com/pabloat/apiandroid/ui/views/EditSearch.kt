package com.pabloat.apiandroid.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pabloat.apiandroid.data.local.Videojuego
import com.pabloat.apiandroid.navigation.Destinations
import com.pabloat.apiandroid.ui.util.VideojuegosItem
import com.pabloat.apiandroid.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun EditSearch(onNavController: NavHostController, mainViewModel: MainViewModel){
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
                mainViewModel.setSelectedVideojuego(videojuego)
            }
            onNavController.navigate(Destinations.EditScreen.route)

        }) {
            Text("Buscar juego")
        }

        videojuegoEncontrado.value?.let { videojuego ->
            VideojuegosItem(videojuego = videojuego)
        }
    }
}

@Composable
fun EditScreen(onNavController: NavHostController, mainViewModel: MainViewModel){
    val videojuego = mainViewModel.selectedVideojuego.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var editedTitle by remember { mutableStateOf<String>(videojuego?.title ?: "") }
        TextField(
            value = editedTitle,
            onValueChange = { editedTitle = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        var editedThumbnail by remember { mutableStateOf<String>(videojuego?.thumbnail ?: "") }
        TextField(
            value = editedThumbnail,
            onValueChange = { editedThumbnail = it },
            label = { Text("Thumbnail") },
            modifier = Modifier.fillMaxWidth()
        )

        var editedDeveloper by remember { mutableStateOf(videojuego?.developer ?: "") }
        TextField(
            value = editedDeveloper,
            onValueChange = { editedDeveloper = it },
            label = { Text("Desarrollador") },
            modifier = Modifier.fillMaxWidth()
        )

        var editedDescription by remember { mutableStateOf(videojuego?.shortDescription ?: "") }
        TextField(
            value = editedDescription,
            onValueChange = { editedDescription = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        var editedGenre by remember { mutableStateOf(videojuego?.genre ?: "") }
        TextField(
            value = editedGenre,
            onValueChange = { editedGenre = it },
            label = { Text("Género") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val editedVideojuego = videojuego?.copy(
                    title = editedTitle,
                    thumbnail = editedThumbnail,
                    developer = editedDeveloper,
                    shortDescription = editedDescription,
                    genre = editedGenre
                )
                if (editedVideojuego != null) {
                    mainViewModel.updateVideoGame(editedVideojuego)
                }
                onNavController.navigate(Destinations.ManageScreen.route)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Guardar cambios")
        }
    }
}
