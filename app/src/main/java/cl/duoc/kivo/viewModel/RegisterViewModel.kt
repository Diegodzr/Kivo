package cl.duoc.kivo.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.repository.RegisterRepository
import cl.duoc.kivo.model.RegisterModel
import cl.duoc.kivo.model.MensajeError

class RegisterViewModel : ViewModel() {
    private val repository = RegisterRepository()

    var register: RegisterModel by mutableStateOf( repository.getRegister() )
        private set

    var mensajesError: MensajeError by mutableStateOf( repository.getMensajesError() )
        private set

    fun onNombreChange(nombre: String) {
        register = register.copy(nombre = nombre)
    }

    fun onCorreoChange(correo: String) {
        register = register.copy(correo = correo)
    }

    fun onEdadChange(edad: String) {
        register = register.copy(edad = edad)
    }

    fun onTerminosChange(acepta: Boolean) {
        register = register.copy(terminos = acepta)
    }

    fun verificarRegistro(): Boolean {
        return verificarNombre() &&
                verificarCorreo() &&
                verificarEdad() &&
                verificarTerminos()
    }

    fun verificarNombre(): Boolean {
        val esValido = repository.validacionNombre(register.nombre)
        mensajesError = if (!esValido) {
            mensajesError.copy(nombre = "El nombre no puede estar vacío")
        } else {
            mensajesError.copy(nombre = "")
        }
        return esValido
    }

    fun verificarCorreo(): Boolean {
        val esValido = repository.validacionCorreo(register.correo)
        mensajesError = if(!esValido) {
            mensajesError.copy(correo = "El correo no es válido")
        } else {
            mensajesError.copy(correo = "")
        }
        return esValido
    }

    fun verificarEdad(): Boolean {
        val esValido = repository.validacionEdad(register.edad)
        mensajesError = if(!esValido) {
            mensajesError.copy(edad = "La edad debe ser un número entre 0 y 120")
        } else {
            mensajesError.copy(edad = "")
        }
        return esValido
    }

    fun verificarTerminos(): Boolean {
        val esValido = repository.validacionTerminos(register.terminos)
        mensajesError = if(!esValido) {
            mensajesError.copy(terminos = "Debes aceptar los términos")
        } else {
            mensajesError.copy(terminos = "")
        }
        return esValido
    }

    fun registrarCuenta() {
        if (verificarRegistro()) {
            repository.registrarUsuario(register)
        }
    }
}