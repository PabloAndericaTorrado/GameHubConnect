package com.pabloat.apiandroid.ui.util

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pabloat.apiandroid.data.local.Videojuego


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideojuegosItem(videojuego: Videojuego) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        onClick = {
            Log.d("CARD", videojuego.toString())
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = videojuego.title,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Desarrollador: ${videojuego.developer}")
            Text(text = "Descripcion:\n${videojuego.shortDescription}")
            Text(text = "Género: ${videojuego.genre}")
            Text(text = "Plataforma: ${videojuego.platform}")
            Text(text = "Fecha Salida: ${videojuego.date}")

        }
    }
}
