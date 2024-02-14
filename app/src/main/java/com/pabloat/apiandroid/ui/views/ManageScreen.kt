package com.pabloat.apiandroid.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pabloat.apiandroid.navigation.Destinations
import com.pabloat.apiandroid.viewmodel.MainViewModel
//navController.navigate(Destinations.DayScreen.route)
@Composable
fun ManageScreen(navHostController: NavHostController, mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Gestión de Videojuegos", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
        Button(
            onClick = { navHostController.navigate(Destinations.MainScreen.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Ver Juegos")
        }

        Button(
            onClick = { /* TODO: Implement edit action */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Editar Juego")
        }

        Button(
            onClick = { /* TODO: Implement add action */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Añadir Juego")
        }

        Button(
            onClick = { /* TODO: Implement delete action */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Borrar Juego")
        }
    }
}
