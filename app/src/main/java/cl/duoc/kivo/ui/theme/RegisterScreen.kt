package cl.duoc.kivo.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import cl.duoc.kivo.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavHostController, viewModel: RegisterViewModel) {

    var abrirModal by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 360.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Regístrate en Kivo",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 40.dp)
                )

                OutlinedTextField(
                    value = viewModel.nombre.value.valor,
                    onValueChange = { viewModel.onNombreChange(it) },
                    label = { Text("Nombre") },
                    isError = viewModel.nombre.value.error.isNotEmpty(),
                    supportingText = {
                        if (viewModel.nombre.value.error.isNotEmpty())
                            Text(
                                viewModel.nombre.value.error,
                                color = MaterialTheme.colorScheme.error
                            )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = viewModel.correo.value.valor,
                    onValueChange = { viewModel.onCorreoChange(it) },
                    label = { Text("Correo") },
                    isError = viewModel.correo.value.error.isNotEmpty(),
                    supportingText = {
                        if (viewModel.correo.value.error.isNotEmpty())
                            Text(
                                viewModel.correo.value.error,
                                color = MaterialTheme.colorScheme.error
                            )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = viewModel.clave.value.valor,
                    onValueChange = { viewModel.onClaveChange(it) },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = viewModel.clave.value.error.isNotEmpty(),
                    supportingText = {
                        if (viewModel.clave.value.error.isNotEmpty())
                            Text(
                                viewModel.clave.value.error,
                                color = MaterialTheme.colorScheme.error
                            )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = viewModel.edad.value.valor,
                    onValueChange = { viewModel.onEdadChange(it) },
                    label = { Text("Edad") },
                    isError = viewModel.edad.value.error.isNotEmpty(),
                    supportingText = {
                        if (viewModel.edad.value.error.isNotEmpty())
                            Text(
                                viewModel.edad.value.error,
                                color = MaterialTheme.colorScheme.error
                            )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = viewModel.terminos.value,
                        onCheckedChange = { viewModel.onTerminosChange(it) }
                    )
                    Text("Acepta los términos")
                }

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    enabled = viewModel.verificarRegistro(),
                    onClick = { if (viewModel.registrar()) abrirModal = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrarse")
                }

                Text(
                    text = "Volver al Inicio",
                    modifier = Modifier
                        .clickable { navController.navigate("login") }
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (abrirModal) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text("Confirmación") },
                    text = { Text("Registrado correctamente") },
                    confirmButton = {
                        Button(onClick = { navController.navigate("login") }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}


