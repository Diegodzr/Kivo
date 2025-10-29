package cl.duoc.kivo.repository

import cl.duoc.kivo.model.RegisterModel

object RegisterRepository {

    // Lista de usuarios en memoria
    private val listaUsuarios = mutableListOf<RegisterModel>(
        // Usuario de prueba
        RegisterModel(
            nombre = "Sara",
            correo = "Sara@kivo.com",
            clave = "123456",
            edad = "25",
            terminos = true
        )
    )

    fun registrarUsuario(usuario: RegisterModel) {
        listaUsuarios.add(usuario)
    }

    fun existeUsuario(correo: String): Boolean {
        return listaUsuarios.any { it.correo == correo }
    }

    fun validarLogin(email: String, clave: String): Boolean {
        return listaUsuarios.any { it.correo == email && it.clave == clave }
    }

    fun getUsuarioPorCorreo(email: String): RegisterModel? {
        return listaUsuarios.find { it.correo == email }
    }
}
