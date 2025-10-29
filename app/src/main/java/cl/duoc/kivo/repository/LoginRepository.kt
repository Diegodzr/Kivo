package cl.duoc.kivo.repository

import cl.duoc.kivo.model.LoginModel

class LoginRepository {

    fun getLogin(): LoginModel = LoginModel()

    fun validarCredenciales(email: String, clave: String): Boolean {
        return RegisterRepository.validarLogin(email, clave)
    }
}
