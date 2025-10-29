package cl.duoc.kivo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cl.duoc.kivo.R
import cl.duoc.kivo.viewModel.LoginViewModel
import cl.duoc.kivo.viewModel.PerfilViewModel

@Composable
fun PerfilScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    perfilViewModel: PerfilViewModel = viewModel()

) {
    // Obtener usuario actual desde LoginViewModel
    val usuario = loginViewModel.usuarioActual.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp, vertical = 80.dp), // más espacio arriba
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(R.drawable.perfil), // reemplaza con tu imagen
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre del usuario
        Text(
            text = usuario?.nombre ?: "Usuario",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Correo del usuario
        Text(
            text = usuario?.correo ?: "correo@kivo.com",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Tarjeta con datos adicionales
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Información de la cuenta",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Nombre: ${usuario?.nombre ?: "Usuario"}", color = Color.Gray)
                Text("Correo: ${usuario?.correo ?: "correo@kivo.com"}", color = Color.Gray)
                Text("Registrado en Kivo", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para volver a lecciones
        Button(
            onClick = { navController.navigate("leccion") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a Lecciones")
        }
    }
}