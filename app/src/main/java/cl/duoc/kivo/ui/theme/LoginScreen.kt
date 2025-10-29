package cl.duoc.kivo.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cl.duoc.kivo.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel) {

    var mensajeError by remember { mutableStateOf("") }

    Scaffold { inner ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .widthIn(max = 360.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ✅ TÍTULO coherente con Register (32sp, bold)
                Text(
                    text = "Kivo",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 40.dp)
                )

                // ✅ Campo de correo
                OutlinedTextField(
                    value = loginViewModel.login.value.email,
                    onValueChange = { loginViewModel.onEmailChange(it) },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    isError = mensajeError.isNotEmpty()
                )

                // ✅ Espaciado uniforme como Register
                Spacer(modifier = Modifier.height(12.dp))

                // ✅ Campo de contraseña
                OutlinedTextField(
                    value = loginViewModel.login.value.clave,
                    onValueChange = { loginViewModel.onClaveChange(it) },
                    label = { Text("Clave") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    isError = mensajeError.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(25.dp))

                // ✅ Botón coherente
                Button(
                    onClick = {
                        if (loginViewModel.validarLogin()) {
                            mensajeError = ""
                            navController.navigate("leccion") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            mensajeError = "Correo o clave incorrectos"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Iniciar Sesión")
                }

                // ✅ Texto de error estilo Register
                if (mensajeError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mensajeError,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ✅ Texto clickeable coherente con "Volver al inicio"
                Text(
                    text = "¿No tienes cuenta? Regístrate",
                    modifier = Modifier
                        .clickable { navController.navigate("register") }
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
