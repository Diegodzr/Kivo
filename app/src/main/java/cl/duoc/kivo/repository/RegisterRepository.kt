package cl.duoc.kivo.repository

import cl.duoc.kivo.model.MensajesError
import cl.duoc.kivo.model.UsuarioRegister

class RegisterRepository {

    fun getRegister(): UsuarioRegister = UsuarioRegister()
    fun getMensajesError(): MensajesError = MensajesError()

    fun validacionNombre(nombre: String): Boolean {
        return nombre.isNotBlank()
    }

    fun validacionCorreo(correo: String): Boolean {
        return correo.matches(Regex("^[\\w.-]+@[\\w.-]+\\.\\w+$"))
    }

    fun validacionEdad(edad: String): Boolean {
        val edadInt = edad.toIntOrNull()
        return edadInt != null && edadInt in 0..120
    }

    fun validacionTerminos(terminos: Boolean): Boolean {
        return terminos
    }
}