package cl.duoc.kivo.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.RegisterModel
import cl.duoc.kivo.repository.RegisterRepository

class PerfilViewModel : ViewModel() {

    // Usuario logueado
    var usuario: MutableState<RegisterModel?> = mutableStateOf(null)
        private set

    // Función para cargar el usuario por correo
    fun cargarUsuario(email: String) {
        usuario.value = RegisterRepository.getUsuarioPorCorreo(email)
    }
}
