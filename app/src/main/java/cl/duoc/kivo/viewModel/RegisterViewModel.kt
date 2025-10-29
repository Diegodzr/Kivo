package cl.duoc.kivo.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.RegisterModel
import cl.duoc.kivo.repository.RegisterRepository

class RegisterViewModel : ViewModel() {

    data class CampoEstado(
        val valor: String = "",
        val tocado: Boolean = false,
        val error: String = ""
    )

    var nombre = mutableStateOf(CampoEstado())
    var correo = mutableStateOf(CampoEstado())
    var clave = mutableStateOf(CampoEstado())
    var edad = mutableStateOf(CampoEstado())
    var terminos = mutableStateOf(false)

    fun onNombreChange(valor: String) {
        nombre.value = nombre.value.copy(valor = valor, tocado = true)
        validarNombre()
    }

    fun onCorreoChange(valor: String) {
        correo.value = correo.value.copy(valor = valor, tocado = true)
        validarCorreo()
    }

    fun onClaveChange(valor: String) {
        clave.value = clave.value.copy(valor = valor, tocado = true)
        validarClave()
    }

    fun onEdadChange(valor: String) {
        edad.value = edad.value.copy(valor = valor, tocado = true)
        validarEdad()
    }

    fun onTerminosChange(valor: Boolean) {
        terminos.value = valor
    }

    // ---- VALIDACIONES ----
    private fun validarNombre() {
        val errorMsg = if (nombre.value.valor.length < 3) "Debe tener al menos 3 caracteres" else ""
        nombre.value = nombre.value.copy(error = if(nombre.value.tocado) errorMsg else "")
    }

    private fun validarCorreo() {
        val errorMsg = if (!correo.value.valor.contains("@")) "Correo inválido" else ""
        correo.value = correo.value.copy(error = if(correo.value.tocado) errorMsg else "")
    }

    private fun validarClave() {
        val errorMsg = if (clave.value.valor.length < 6) "Clave debe tener al menos 6 caracteres" else ""
        clave.value = clave.value.copy(error = if(clave.value.tocado) errorMsg else "")
    }

    private fun validarEdad() {
        val edadNum = edad.value.valor.toIntOrNull()
        val errorMsg = if (edadNum == null || edadNum < 10 || edadNum > 99) "Edad inválida" else ""
        edad.value = edad.value.copy(error = if(edad.value.tocado) errorMsg else "")
    }

    fun verificarRegistro(): Boolean {
        validarNombre()
        validarCorreo()
        validarClave()
        validarEdad()
        return nombre.value.error.isEmpty() &&
                correo.value.error.isEmpty() &&
                clave.value.error.isEmpty() &&
                edad.value.error.isEmpty() &&
                terminos.value
    }

    fun registrar(): Boolean {
        if (!verificarRegistro()) return false

        // Evita duplicado
        if (RegisterRepository.existeUsuario(correo.value.valor)) return false

        val nuevoUsuario = RegisterModel(
            nombre = nombre.value.valor,
            correo = correo.value.valor,
            clave = clave.value.valor,
            edad = edad.value.valor,
            terminos = terminos.value
        )

        RegisterRepository.registrarUsuario(nuevoUsuario)
        return true
    }
}
