package cl.duoc.kivo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.duoc.kivo.R

@Composable
fun Leccion(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menú hamburguesa (sin funcionalidad)
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menú",
                modifier = Modifier.size(32.dp)
            )

            // Logo de KIVO
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_hand_logo),
                    contentDescription = "Logo mano",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("KIVO", style = MaterialTheme.typography.titleLarge)
            }

            // Ícono de casa (redirige al perfil)
            Image(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Inicio",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        navController.navigate("login")
                    }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Contenido principal (letra A y seña)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("A", style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.img_sign_a),
                contentDescription = "Seña letra A",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}