package cl.duoc.kivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import cl.duoc.kivo.ui.theme.AppNavigation
import cl.duoc.kivo.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // Creamos el NavController
                val navController = rememberNavController()

                // Pasamos el navController a la navegación
                AppNavigation(navController = navController)
            }
        }
    }
}
