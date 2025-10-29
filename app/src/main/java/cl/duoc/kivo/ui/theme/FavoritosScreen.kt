package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cl.duoc.kivo.viewModel.FavoritoViewModel

@Composable
fun FavoritosScreen(navController: NavHostController, favoritoViewModel: FavoritoViewModel) {
    var palabra by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {

        // Título centrado de la sección
        Text(
            text = "Palabras Favoritas",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Formulario para añadir nueva palabra justo debajo del título
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = palabra,
                onValueChange = { palabra = it },
                label = { Text("Título de la palabra") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción (opcional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (palabra.isNotBlank()) {
                        favoritoViewModel.agregarFavorito(palabra, descripcion)
                        palabra = ""
                        descripcion = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Agregar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de palabras favoritas
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) // espacio uniforme entre tarjetas
        ) {
            items(favoritoViewModel.favoritos) { fav ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFD580) // Amarillo/naranja suave
                    ),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        // Título alineado a la izquierda
                        Text(
                            text = fav.titulo,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (fav.descripcion.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = fav.descripcion,
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}
