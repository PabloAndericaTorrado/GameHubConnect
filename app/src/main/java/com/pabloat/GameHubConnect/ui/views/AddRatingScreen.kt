import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.data.local.Videojuego
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.ui.views.VolverAtras
import com.pabloat.GameHubConnect.viewmodel.MainViewModel

@Composable
fun AddRatingScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    videojuego: Videojuego
) {
    var gameRate by remember { mutableStateOf(videojuego.valoracion) }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Añadir Valoración al Juego: ${videojuego.title}",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        ValoracionTextField(
            value = gameRate,
            onValueChange = { newRate ->
                gameRate = newRate
                error = newRate !in 0.0f..10.0f
            },
            isError = error
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (error) {
            Text(
                text = "La valoración debe estar entre 0 y 10",
                color = androidx.compose.ui.graphics.Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        GuardadoButton(
            onClick = {
                mainViewModel.updateVideojuegoRating(videojuego.id, gameRate)
                navHostController.navigateUp()
            },
            isEnabled = !error
        )

        Spacer(modifier = Modifier.height(16.dp))

        VolverAtras(
            navHostController = navHostController,
            route = Destinations.ManageScreen.route,
            text = "Volver al menú"
        )
    }
}

@Composable
private fun ValoracionTextField(
    value: Float,
    onValueChange: (Float) -> Unit,
    isError: Boolean
) {
    var textValue by remember(value) { mutableStateOf(value.toString()) }

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue ->
            textValue = newValue
            onValueChange(newValue.toFloatOrNull() ?: 0.0f)
        },
        label = { Text("Valoración Personal del Juego") },
        modifier = Modifier.fillMaxWidth(),
        isError = isError
    )
}

@Composable
private fun GuardadoButton(
    onClick: () -> Unit,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Guardar Valoración")
    }
}
