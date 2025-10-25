package cl.duoc.kivo.ui.theme

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.duoc.kivo.R
import cl.duoc.kivo.viewModel.LeccionViewModel

@Composable
fun LeccionScreen(navController: NavController, viewModel: LeccionViewModel = viewModel()) {
    val lecciones by viewModel.lecciones.collectAsState()
    val expandedState by viewModel.expandedState.collectAsState()

    var showModal by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) showModal = true
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) cameraLauncher.launch(null)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // 🔹 Barra superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.ic_menu), contentDescription = "Menú", modifier = Modifier.size(32.dp))
            Image(painter = painterResource(R.drawable.kivo_logo), contentDescription = "Logo", modifier = Modifier.size(130.dp))
            Image(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = "Inicio",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navController.navigate("login") }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 Lecciones
        lecciones.forEachIndexed { index, leccion ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { viewModel.toggleExpansion(index) },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                val innerScroll = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .verticalScroll(innerScroll),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(leccion.nivel, color = MaterialTheme.colorScheme.secondary)
                    Text(leccion.titulo, color = MaterialTheme.colorScheme.primary)

                    if (expandedState[index] == true) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(leccion.imagenResId),
                            contentDescription = "Imagen de ${leccion.titulo}",
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(leccion.descripcion, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            when (PackageManager.PERMISSION_GRANTED) {
                                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> cameraLauncher.launch(null)
                                else -> permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }) { Text("Abrir Cámara") }
                    }
                }
            }
        }
    }

    if (showModal) {
        AlertDialog(
            onDismissRequest = { showModal = false },
            title = { Text("¡Felicidades!") },
            text = { Text("Excelente, aprobaste la lección.") },
            confirmButton = { Button(onClick = { showModal = false }) { Text("OK") } }
        )
    }
}
