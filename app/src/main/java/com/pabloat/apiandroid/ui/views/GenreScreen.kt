package com.pabloat.apiandroid.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pabloat.apiandroid.ui.util.GenreItem
import com.pabloat.apiandroid.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreScreen(mainViewModel: MainViewModel, onGenreSelected: (String) -> Unit) {
    val genres by mainViewModel.generos.collectAsState()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Géneros") }) },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                LazyColumn {
                    items(genres) { genre ->
                        GenreItem(genre = genre) {
                            onGenreSelected(genre)
                        }
                    }
                }
            }
        }
    )
}