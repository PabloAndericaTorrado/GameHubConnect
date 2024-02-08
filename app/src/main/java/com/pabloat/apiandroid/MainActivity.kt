package com.pabloat.apiandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.pabloat.apiandroid.data.local.Categoria
import com.pabloat.apiandroid.data.local.MercadonaDatasource
import com.pabloat.apiandroid.data.local.Producto
import com.pabloat.apiandroid.ui.theme.ApiAndroidTheme
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var mercadonaDatasource: MercadonaDatasource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiAndroidTheme {
        Greeting("Android")
    }
}