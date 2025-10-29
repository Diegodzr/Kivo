package cl.duoc.kivo.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.duoc.kivo.screens.ResenasScreen
import cl.duoc.kivo.ui.screens.FavoritosScreen
import cl.duoc.kivo.viewModel.*

@Composable
fun AppNavigation(navController: NavHostController) {
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()
    val leccionViewModel: LeccionViewModel = viewModel()
    val perfilViewModel: PerfilViewModel = viewModel()
    val resenaViewModel: ResenaViewModel = viewModel()
    val favoritoViewModel: FavoritoViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, loginViewModel) }
        composable("register") { RegisterScreen(navController, registerViewModel) }
        composable("leccion") { LeccionScreen(navController, leccionViewModel, loginViewModel) }
        composable("perfil") { PerfilScreen(navController, loginViewModel, perfilViewModel) }
        composable("resenas") { ResenasScreen(navController, resenaViewModel) }
        composable("favoritos") { FavoritosScreen(navController, favoritoViewModel) }
    }
}
