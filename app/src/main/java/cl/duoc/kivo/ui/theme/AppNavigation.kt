package cl.duoc.kivo.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.kivo.viewModel.LoginViewModel
import cl.duoc.kivo.viewModel.RegisterViewModel



@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = loginViewModel, navController = navController)
        }
        composable("register") {
            Register(viewModel = registerViewModel, navController = navController)
        }

        composable("leccion") {
            LeccionScreen(navController)
        }
    }
}