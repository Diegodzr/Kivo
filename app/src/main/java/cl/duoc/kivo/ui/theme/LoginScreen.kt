package cl.duoc.kivo.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import cl.duoc.kivo.viewModel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido a Kivo")
        OutlinedTextField(
            value = viewModel.login.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Correo electrónico") }
        )
        OutlinedTextField(
            value = viewModel.login.clave,
            onValueChange = { viewModel.onClaveChange(it) },
            label = { Text("Clave") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { /*TODO*/ }) {
            Text("Iniciar sesión")
        }
        Text(
            text = "¿Aún no tienes cuenta? Regístrate.",
            modifier = Modifier.clickable { navController.navigate("register") },
            color = Color.Blue
        )
    }
}
