package ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import cl.duocuc.app.R

// IMPORTANTE: asegúrate de que el recurso exista en res/drawable con nombre "logo" (logo.png, logo.xml, etc.)
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onButtonClick: (() -> Unit)? = null) {
    // Estado local: contador de clicks (demuestra cómo Compose maneja estado)
    var clicks by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mi aplicación") }
            )
        }
    ) { innerPadding ->
        // Column que ocupa el espacio disponible; aplicamos innerPadding que entrega Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)      // padding introducido por el Scaffold (top bar, etc.)
                .padding(16.dp),            // padding interior estándar
            verticalArrangement = Arrangement.spacedBy(20.dp), // espacio entre elementos
            horizontalAlignment = Alignment.CenterHorizontally  // centra contenido horizontalmente
        ) {
            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(onClick = {
                clicks++
                onButtonClick?.invoke()
            }) {
                Text(text = "Clicka")
            }

            Text(text = "Has hecho clic $clicks veces")

            // Imagen desde recursos: coloca un archivo en res/drawable/logo.png
            Image(
                painter = painterResource(id = R.drawable.logo), // <- R.drawable.logo
                contentDescription = "Logo de la aplicación",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit
            )

            // Spacer con weight empuja el contenido anterior hacia arriba y deja el footer abajo
            Spacer(modifier = Modifier.weight(1f))

            // Footer simple
            Text(
                text = "© ${java.time.Year.now().value} Mi App",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}
