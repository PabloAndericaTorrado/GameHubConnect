package com.pabloat.GameHubConnect.ui.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.pabloat.GameHubConnect.data.local.Videojuego
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*@Composable
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
}*/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideojuegoCard(
    onNavController: NavHostController,
    videojuego: Videojuego,
    mainViewModel: MainViewModel
) {
    var isCardElevated by remember { mutableStateOf(false) }
    val cardElevation by animateDpAsState(
        if (isCardElevated) 16.dp else 8.dp,
        tween(500),
        label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            isCardElevated = !isCardElevated
            mainViewModel.setSelectedVideojuegoId(videojuego.id)
            Log.d("MV", "Pulsdo")
            onNavController.navigate(Destinations.DetailGameScreen.route)
        },
        shape = RoundedCornerShape(16.dp),
        elevation = cardElevation,
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = videojuego.thumbnail)
                        .apply(block = fun ImageRequest.Builder.() {
                            transformations(RoundedCornersTransformation(10f))
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = videojuego.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun VideojuegoDetailCard(
    onNavController: NavHostController,
    videojuego: Videojuego,
    mainViewModel: MainViewModel,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {

    val onFavoriteClicked = {
        val success = mainViewModel.saveGameArray(videojuego)
        coroutineScope.launch {
            val message = if (success) "Se ha añadido el videojuego a tu lista de deseos" else "El videojuego ya se encuentra en tu lista de deseos"
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .height(580.dp)
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            GenreTag(videojuego.genre)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = videojuego.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            VideojuegoThumbnail(videojuego.thumbnail)

            Spacer(modifier = Modifier.height(25.dp))

            VideojuegoDetailRow(
                label = "Desarrollador:",
                value = videojuego.developer
            )
            VideojuegoDetailRow(
                label = "Plataforma:",
                value = videojuego.platform
            )
            VideojuegoDetailRow(
                label = "Fecha de lanzamiento:",
                value = videojuego.date
            )
            VideojuegoDetailRow(
                label = "Valoración:",
                value = videojuego.valoracion.toString()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = videojuego.shortDescription,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.weight(0.8f))

            OutlinedButton(
                onClick = {
                    onNavController.navigate(Destinations.AddRatingScreen.route)
                    mainViewModel.saveGame(videojuego)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Añadir valoración",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
            OutlinedButton(
                onClick = {
                    onFavoriteClicked()
                    mainViewModel.saveGameArray(videojuego)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Guardar",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Composable
private fun GenreTag(genre: String) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFFD1D5E1),
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Text(
            text = genre,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
        )
    }
}

@Composable
private fun VideojuegoThumbnail(thumbnail: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9),
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = thumbnail).apply(block = fun ImageRequest.Builder.() {
                    transformations(RoundedCornersTransformation(10f))
                }).build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun VideojuegoDetailRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideojuegosDeleteItem(videojuego: Videojuego, onDeleteClick: () -> Unit) {
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

        OutlinedButton(onClick = { onDeleteClick() }) {
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
fun NavigationBottomBar(navHostController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationItem(Icons.Filled.Home, "Inicio", "InitScreen"),
        NavigationItem(Icons.AutoMirrored.Filled.ListAlt, "Catálogo", "GenreScreen"),
        NavigationItem(Icons.Filled.Favorite, "Favoritos", "UserFavScreen"),
        NavigationItem(Icons.Filled.AccountCircle, "Mi Cuenta", "ProfileScreen")
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        containerColor = Color(0xFA090F16),
        contentColor = Color.White
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
                    onClick = {
                        selectedItem = index
                        navHostController.navigate(item.route)
                    },
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
        targetValue = if (selected) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) Color.White else Color.LightGray,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    val iconColor by animateColorAsState(
        targetValue = if (selected) Color.White else Color.LightGray,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
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

fun isConnectedToInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}