package com.pabloat.GameHubConnect.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.data.local.Videojuego
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.viewmodel.MainViewModel


@Composable
fun AddRatingScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    videojuego: Videojuego
) {
    val gameRate = remember { mutableFloatStateOf(0.0f) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Añadir Valoración al Juego: ${videojuego.title}",
            modifier = Modifier.fillMaxWidth()
        )
        CuadroFloat(value = gameRate, label = "Valoracion Personal del Juego", videojuego)
        // Cuando el valor de gameRate cambie, actualiza la valoración del videojuego
        Button(onClick = { mainViewModel.updateVideojuegoRating(videojuego.id, gameRate.value) }) {
            Text(text = "Guardar Valoración")
        }
        VolverAtras(
            navHostController = navHostController,
            route = Destinations.ManageScreen.route,
            text = "Volver al menú"
        )

    }

}

@Composable
fun CuadroFloat(value: MutableFloatState, label: String, videojuego: Videojuego) {
    var textValue by remember { mutableStateOf(videojuego.valoracion.toString()) }
    var error by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue ->
            textValue = newValue
            if (newValue.isEmpty()) {
                videojuego.valoracion = 0.0f
            } else if (newValue.toFloatOrNull()!! < 0 || newValue.toFloatOrNull()!! > 10) {
                error = true
            } else {
                value.value = newValue.toFloat()
                error = false
            }
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    if (error) {
        Text(
            text = "La valoración debe estar entre 0 y 10",
            color = androidx.compose.ui.graphics.Color.Red
        )
    }
}