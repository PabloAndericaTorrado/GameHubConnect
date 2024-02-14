package com.pabloat.apiandroid.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pabloat.apiandroid.navigation.Destinations

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun InitScreen(onNavController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(60.dp)
    ) {
        Text("Pablo Andérica Torrado", fontSize = 18.sp)
        Text("Fernando Baquero Zamora", fontSize = 18.sp)
        Text("Manuel Negrete Bozas", fontSize = 18.sp)

        Button(onClick = { onNavController.navigate(Destinations.GenreScreen.route) }) {
            Text("Catálogo de videojuegos")
        }

        Button(onClick = { onNavController.navigate(Destinations.ManageScreen.route) }) {
            Text("Administrar Videojuegos")
        }
    }
}