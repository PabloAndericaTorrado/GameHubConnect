package com.pabloat.GameHubConnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.pabloat.GameHubConnect.data.VideojuegoRepository
import com.pabloat.GameHubConnect.data.local.AppDataBase
import com.pabloat.GameHubConnect.data.local.VideojuegoDatasource
import com.pabloat.GameHubConnect.data.remote.RemoteVideojuegoDataSource
import com.pabloat.GameHubConnect.data.remote.RetrofitBuilder
import com.pabloat.GameHubConnect.navigation.MainNaviation
import com.pabloat.GameHubConnect.navigation.MainTopBar
import com.pabloat.GameHubConnect.viewmodel.FireBaseViewModel
import com.pabloat.GameHubConnect.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainApp()
            }

        }
    }
}

@Composable
fun MainApp() {
    val navHostController = rememberNavController()
    val remoteDatasource = RemoteVideojuegoDataSource(RetrofitBuilder.apiService)
    val localDatasource = VideojuegoDatasource(LocalContext.current)
    val repository = VideojuegoRepository(localDatasource, remoteDatasource)
    val mainViewModel = MainViewModel(repository)
    val firebaseViewModel = FireBaseViewModel()
    AppDataBase.getDatabase(LocalContext.current)
    mainViewModel.getRemoteVideojuego()

    Scaffold(topBar = { MainTopBar() }) { it ->
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        )
        {
            MainNaviation(navHostController, mainViewModel,firebaseViewModel)
        }
    }
}

