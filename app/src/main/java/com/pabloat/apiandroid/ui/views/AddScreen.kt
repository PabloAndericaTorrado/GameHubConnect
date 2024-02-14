package com.pabloat.apiandroid.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pabloat.apiandroid.viewmodel.MainViewModel


@Composable
fun AddScreen(navHostController: NavHostController, mainViewModel: MainViewModel) {
    val gameName = remember { mutableStateOf("") }
    val gameDescription = remember { mutableStateOf("") }
    val gameGenre = remember { mutableStateOf("") }
    val gameImage = remember { mutableStateOf("") }
    val gameDeveloper = remember { mutableStateOf("") }
    val gamePlatform = remember { mutableStateOf("") }
    val gameDate = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = gameName.value,
            onValueChange = { gameName.value = it },
            label = { Text("Nombre del juego") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gameImage.value,
            onValueChange = { gameImage.value = it },
            label = { Text("Imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gameDeveloper.value,
            onValueChange = { gameDeveloper.value = it },
            label = { Text("Desarrollador del juego") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gameDescription.value,
            onValueChange = { gameDescription.value = it },
            label = { Text("Descripción del juego") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gameGenre.value,
            onValueChange = { gameGenre.value = it },
            label = { Text("Género del juego") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gamePlatform.value,
            onValueChange = { gamePlatform.value = it },
            label = { Text("Plataforma") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gameDate.value,
            onValueChange = { gameDate.value = it },
            label = { Text("Fecha de Salida") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Implement add game to database action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir juego")
        }
    }
}

