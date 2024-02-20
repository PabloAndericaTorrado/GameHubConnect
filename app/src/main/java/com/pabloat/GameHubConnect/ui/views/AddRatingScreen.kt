package com.pabloat.GameHubConnect.ui.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.viewmodel.MainViewModel


@Composable
fun AddRatingScreen(navHostController: NavHostController, mainViewModel: MainViewModel, id: Int) {
    val gameRate = remember { mutableFloatStateOf(0.0f) }
    CuadroFloat(value = gameRate, label = "Valoracion Personal del Juego")
    // Cuando el valor de gameRate cambie, actualiza la valoración del videojuego
    LaunchedEffect(gameRate.value) {
        mainViewModel.updateVideojuegoRating(id, gameRate.value)
    }
}

@Composable
fun CuadroFloat(value: MutableFloatState, label: String) {
    var textValue by remember { mutableStateOf(value.value.toString()) }

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue ->
            textValue = newValue
            value.value = newValue.toFloatOrNull() ?: 0.0f
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))
}