package cl.duoc.kivo.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.duoc.kivo.viewModel.RegisterViewModel


@Composable
fun Register(viewModel: RegisterViewModel, navController: NavController) {

    var abrirModal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.register.nombre,
            onValueChange = { viewModel.onNombreChange(it) },
            label = { Text("Ingresa nombre") },
            isError = !viewModel.verificarNombre(),
            supportingText = { Text( viewModel.mensajesError.nombre, color = androidx.compose.ui.graphics.Color.Red) }
        )
        OutlinedTextField(
            value = viewModel.register.correo,
            onValueChange = { viewModel.onCorreoChange(it) },
            label = { Text("Ingresa correo") },
            isError = !viewModel.verificarCorreo(),
            supportingText = { Text( viewModel.mensajesError.correo, color = androidx.compose.ui.graphics.Color.Red) }
        )
        OutlinedTextField(
            value = viewModel.register.edad,
            onValueChange = { viewModel.onEdadChange(it) },
            label = { Text("Ingresa edad") },
            isError = !viewModel.verificarEdad(),
            supportingText = { Text( viewModel.mensajesError.edad, color = androidx.compose.ui.graphics.Color.Red) }
        )
        Checkbox(
            checked = viewModel.register.terminos,
            onCheckedChange = { viewModel.onTerminosChange(it) },
        )
        Text("Acepta los términos")

        Button(
            enabled = viewModel.verificarRegistro(),
            onClick = {
                if(viewModel.verificarRegistro()) {
                    abrirModal = true
                }
            }
        ) {
            Text("Registrarse")
        }

        if (abrirModal) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Confirmación") },
                text = { Text("Registrado correctamente") },
                confirmButton = {
                    Button(onClick = { navController.navigate("login") }) { Text("OK") }
                }
            )
        }

    }
}