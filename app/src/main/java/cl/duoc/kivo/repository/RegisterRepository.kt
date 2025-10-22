package cl.duoc.kivo.repository

import cl.duoc.kivo.model.MensajeError
import cl.duoc.kivo.model.RegisterModel

class RegisterRepository {

    private val cuentasRegistradas = mutableListOf<RegisterModel>()
    fun getRegister(): RegisterModel = RegisterModel()
    fun getMensajesError(): MensajeError = MensajeError()

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

    // NUEVO: registrar usuario
    fun registrarUsuario(usuario: RegisterModel) {
        cuentasRegistradas.add(usuario)
    }

    // NUEVO: verificar si existe usuario con correo y contraseña (o solo correo aquí)
    fun existeUsuario(correo: String): Boolean {
        return cuentasRegistradas.any { it.correo == correo }
    }

    // Opcional: obtener usuario por correo
    fun obtenerUsuario(correo: String): RegisterModel? {
        return cuentasRegistradas.find { it.correo == correo }
    }
}