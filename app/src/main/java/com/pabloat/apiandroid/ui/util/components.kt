package com.pabloat.apiandroid.ui.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pabloat.apiandroid.data.local.Weather
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/* Funciones composables empleadas en la lista */
@Composable
fun ErrorBlock(message: String, onRetryClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = message, style = TextStyle(color = Color.Red, textAlign = TextAlign.Center))
        Button(onClick = { onRetryClick() }) {
            Text(text = "Reintentar")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherItem(weather: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        onClick = {
            Log.d("CARD", weather.toString())
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            //Sobre formato de fechas. https://www.baeldung.com/kotlin/dates
            val date = LocalDate.parse(weather.time)
            Text(
                text = "Fecha: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fontWeight = FontWeight.Bold
            )
            Text(text = "Temperatura maxima: " + weather.temperatureMax)
            Text(text = "Temperatura minima: " + weather.temperatureMin)
            Text(text = "Dato de las pos. ${weather.latitude} | ${weather.longitude}")
        }
    }
}