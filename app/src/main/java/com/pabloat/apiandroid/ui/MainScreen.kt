package com.pabloat.apiandroid.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pabloat.apiandroid.viewmodel.MainViewModel

@Composable
fun MainScreen(vm: MainViewModel, onNavToLista: () -> Unit) {
    val weatherInfo by vm.weathers.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(text = "Tiempo local y API", fontSize = 24.sp)
        Spacer(Modifier.size(128.dp))
        Text("${weatherInfo}", modifier = Modifier.weight(1F))

        ButtonGetWeather(vm)

        Button(onNavToLista) {
            Text("Pasar al modo listado")
        }
    }
}

@Composable
fun ButtonGetWeather(vm: MainViewModel) {
    Button(onClick = {
        val response = vm.getRemoteWeather(
            longitude = -6.9706100f,
            latitude = 38.87789f
        ) //Coordenadas de Badajoz
        Log.d("UI", response.toString())
        //}
    }) {
        Text(text = "Obtener datos de tiempo de Badajoz")
    }
}