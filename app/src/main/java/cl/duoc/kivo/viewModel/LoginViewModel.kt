package cl.duoc.kivo.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cl.duoc.kivo.model.LoginModel
import cl.duoc.kivo.model.RegisterModel
import cl.duoc.kivo.repository.RegisterRepository

class LoginViewModel : ViewModel() {

    // Datos escritos en el formulario de login
    var login: MutableState<LoginModel> = mutableStateOf(LoginModel())
        private set

    // Usuario que inició sesión
    var usuarioActual: MutableState<RegisterModel?> = mutableStateOf(null)
        private set


    // -------------------------------
    // ✅ Actualizar email
    // -------------------------------
    fun onEmailChange(newEmail: String) {
        login.value = login.value.copy(email = newEmail)
    }

    // ✅ Actualizar clave
    fun onClaveChange(newClave: String) {
        login.value = login.value.copy(clave = newClave)
    }


    // -------------------------------
    // ✅ Validar credenciales
    // -------------------------------
    fun validarLogin(): Boolean {
        val email = login.value.email.trim()
        val clave = login.value.clave.trim()

        val esValido = RegisterRepository.validarLogin(email, clave)

        if (esValido) {
            usuarioActual.value = RegisterRepository.getUsuarioPorCorreo(email)
        } else {
            usuarioActual.value = null
        }

        return esValido
    }


    // -------------------------------
    // ✅ Obtener usuario logueado ACTUAL
    // Esto lo usa Perfil, Favoritos, Reseñas, etc.
    // -------------------------------
    fun getUsuarioActual(): RegisterModel? {
        return usuarioActual.value
    }

    // ✅ Opción extra: obtener por correo
    fun getUsuarioPorCorreo(): RegisterModel? {
        val email = login.value.email.trim()
        return RegisterRepository.getUsuarioPorCorreo(email)
    }
}
