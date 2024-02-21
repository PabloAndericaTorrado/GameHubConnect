
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pabloat.GameHubConnect.R
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.viewmodel.FireBaseViewModel

@Composable
fun InitScreen(onNavController: NavHostController, firebaseviewModel: FireBaseViewModel) {
    val background: Painter = painterResource(id = R.drawable.inicio)
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        visible = true
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = background,
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandVertically(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)),
                exit = fadeOut()
            ) {
                Text(
                    "¡Descubre tu próximo juego favorito!",
                    style = MaterialTheme.typography.titleMedium .copy(color = MaterialTheme.colorScheme.background, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(Modifier.height(1.dp))
            }

            Column (
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Desarrollado por: \n" +
                            "Pablo Andérica Torrado\n" +
                            "Fernando Baquero Zamora\n" +
                            "Manuel Negrete Bozas",
                    style = MaterialTheme.typography.bodySmall .copy(color = MaterialTheme.colorScheme.background, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(8.dp)
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(20.dp))
            CategoryCard(title = "¡Explorar!", icon = Icons.Default.Gamepad, onNavController = onNavController, Destinations.GenreScreen.route)
            Spacer(Modifier.height(12.dp))
            if (firebaseviewModel.getStoredEmail() == "admin@admin.com") {
                CategoryCard(title = "Administrar videojuegos", icon = Icons.Default.Settings, onNavController = onNavController, Destinations.ManageScreen.route)
            }
        }
    }
}

@Composable
fun CategoryCard(title: String, icon: ImageVector, onNavController: NavHostController, route: String) {
    val colors = listOf(Color.Red, Color.Magenta, Color.Blue, Color.Cyan, Color.Green, Color.Yellow, Color.Red)
    val animatedIndex by animateFloatAsState(
        targetValue = colors.size.toFloat() - 1,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = { it }),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val startIndex = (animatedIndex % (colors.size - 1)).toInt()
    val endIndex = (startIndex + 1) % colors.size

    val brush = Brush.linearGradient(
        colors = listOf(colors[startIndex], colors[endIndex])
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 3.dp, brush = brush, shape = RoundedCornerShape(12.dp))
            .padding(2.dp)
    ) {
        Card(
            modifier = Modifier
                .clickable { onNavController.navigate(route) }
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.01f))
            ,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                Spacer(Modifier.size(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        }
    }
}

