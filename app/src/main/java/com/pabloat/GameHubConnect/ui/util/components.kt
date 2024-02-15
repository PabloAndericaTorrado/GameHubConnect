package com.pabloat.GameHubConnect.ui.util

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.data.local.Videojuego
@Composable
fun VideojuegoCard(videojuego: Videojuego) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)) {
            Text(text = videojuego.title, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
            Text(text = "Desarrollador: ${videojuego.developer}", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
            Text(text = "Descripcion:\n${videojuego.shortDescription}", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
            Text(text = "Género: ${videojuego.genre}", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
            Text(text = "Plataforma: ${videojuego.platform}", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
            Text(text = "Fecha Salida: ${videojuego.date}", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideojuegosDeleteItem(videojuego: Videojuego, onDeleteClick: () -> Unit){
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

        OutlinedButton(onClick = {onDeleteClick()}) {
            Text(text = "Borrar")
        }
    }
}

@Composable
fun GenreItem(genre: String, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        headlineContent = {
            Text(text = genre)
        }
    )
}

/****************************************************************************************************/
/************************************BARRA DE NAVEGACIÓN*********************************************/
/****************************************************************************************************/
@Composable
fun NavigationBottomBar(onNavHostController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationItem(Icons.Filled.Home, "Inicio", "InitScreen"),
        NavigationItem(Icons.AutoMirrored.Filled.ListAlt, "Catálogo", "GenreScreen"),
        NavigationItem(Icons.Filled.AccountCircle, "Perfil", "profile")
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                NavigationBottomBarItem(
                    icon = item.icon,
                    label = item.label,
                    selected = selectedItem == index,
                    onClick = { selectedItem = index
                        onNavHostController.navigate(item.route)  },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun NavigationBottomBarItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.1f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) Color.Black else Color.Gray,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Column(
        modifier = modifier
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = textColor
        )
    }
}

data class NavigationItem(val icon: ImageVector, val label: String, val route: String)