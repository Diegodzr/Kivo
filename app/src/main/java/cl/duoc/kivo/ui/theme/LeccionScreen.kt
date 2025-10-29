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
import cl.duoc.kivo.R
import cl.duoc.kivo.viewModel.LeccionViewModel
import cl.duoc.kivo.viewModel.LoginViewModel
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import kotlinx.coroutines.launch
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeccionScreen(
    navController: NavHostController,
    leccionViewModel: LeccionViewModel,
    loginViewModel: LoginViewModel
) {
    val lecciones by leccionViewModel.lecciones.collectAsState()
    val expandedState = leccionViewModel.expandedState
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val context = LocalContext.current
    var showModal by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap -> if (bitmap != null) showModal = true }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) cameraLauncher.launch(null)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xCC000000))
                    .padding(top = 100.dp, start = 24.dp)
            ) {
                Text("Menú", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(24.dp))

                val itemStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White)

                Text("Perfil", style = itemStyle, modifier = Modifier.clickable {
                    scope.launch { drawerState.close() }
                    navController.navigate("perfil")
                }.padding(vertical = 12.dp))

                Text("Reseñas", style = itemStyle, modifier = Modifier.clickable {
                    scope.launch { drawerState.close() }
                    navController.navigate("resenas")
                }.padding(vertical = 12.dp))

                Text("Favoritos", style = itemStyle, modifier = Modifier.clickable {
                    scope.launch { drawerState.close() }
                    navController.navigate("favoritos")
                }.padding(vertical = 12.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Image(painterResource(R.drawable.kivo_logo), contentDescription = "Logo", modifier = Modifier.size(130.dp)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(painterResource(R.drawable.img), contentDescription = "Menú")
                        }
                    }
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    lecciones.forEachIndexed { index, leccion ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { leccionViewModel.toggleExpansion(index) },
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(leccion.nivel, color = MaterialTheme.colorScheme.secondary)
                                Text(leccion.titulo, color = MaterialTheme.colorScheme.primary)

                                if (expandedState[index]) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Image(
                                        painter = painterResource(leccion.imagenResId),
                                        contentDescription = "Imagen de ${leccion.titulo}",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
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
            }
        )
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
