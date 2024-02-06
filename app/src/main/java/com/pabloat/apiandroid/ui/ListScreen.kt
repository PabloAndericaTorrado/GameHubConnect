package com.pabloat.apiandroid.ui


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pabloat.apiandroid.ui.util.ErrorBlock
import com.pabloat.apiandroid.ui.util.ScreenState
import com.pabloat.apiandroid.ui.util.WeatherItem
import com.pabloat.apiandroid.viewmodel.MainViewModel
import kotlinx.coroutines.delay


/**
 * ListScreen muestra una lista de items, pero previamente realiza controles para ver si ha podido
 * descargar la información
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListScreen(vm: MainViewModel, navController: NavHostController) {
    val screenState by vm.uiState.collectAsStateWithLifecycle()

    //Esta parte no es necesaria, pero va a provocar que traiga algunos datos a los 2 segundos
    LaunchedEffect(vm) {
        delay(2000)
        vm.getRemoteWeather(longitude = -6.9706100f, latitude = 38.87789f)
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Tiempo local y API", fontSize = 24.sp)

            when (screenState) {
                ScreenState.Loading -> Text(text = "Cargando", fontSize = 48.sp)

                is ScreenState.Error -> ErrorBlock(message = (screenState as ScreenState.Error).message) {}

                is ScreenState.Success ->
                    Column() {
                        val weatherList by vm.weathers.collectAsStateWithLifecycle()
                        LazyColumn(content = {
                            items(weatherList) {
                                WeatherItem(weather = it)
                            }
                        })
                    }
            }
        }
    }
}

