package cl.duoc.kivo.screens

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
import cl.duoc.kivo.viewModel.ResenaViewModel

@Composable
fun ResenasScreen(
    navController: NavHostController,
    resenaViewModel: ResenaViewModel
) {
    var comentario by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("Usuario") } // puedes reemplazarlo con loginViewModel.usuarioActual

    // ✅ Cargar reseñas iniciales del repositorio SOLO UNA VEZ
    LaunchedEffect(Unit) {
        if (resenaViewModel.resenas.isEmpty()) {
            resenaViewModel.cargarResenasIniciales()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp) // evita notch/cámara
    ) {

        // ✅ TÍTULO CENTRADO (igual que Favoritos)
        Text(
            text = "Reseñas",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // ✅ INPUT PARA NUEVA RESEÑA justo debajo del título
        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Escribe tu reseña...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (comentario.isNotBlank()) {
                    resenaViewModel.agregarResena(usuario, comentario)
                    comentario = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Agregar reseña")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ LISTA DE RESEÑAS con diseño similar a Favoritos
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(resenaViewModel.resenas) { resena ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF2CC) // Amarillo suave
                    ),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // Nombre del usuario
                        Text(
                            text = resena.usuario,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Comentario largo
                        Text(
                            text = resena.comentario,
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}
