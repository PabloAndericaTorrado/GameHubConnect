package com.pabloat.apiandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pabloat.apiandroid.data.local.VideojuegoDatasource
import com.pabloat.apiandroid.ui.theme.ApiAndroidTheme

class MainActivity : ComponentActivity() {

    private lateinit var mercadonaDatasource: VideojuegoDatasource

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