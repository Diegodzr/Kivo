package cl.duoc.kivo.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.LoginModel
import cl.duoc.kivo.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    var login: LoginModel by mutableStateOf(repository.getLogin())
        private set

    fun onEmailChange(email: String) {
        login = login.copy(email = email)
    }

    fun onClaveChange(clave: String) {
        login = login.copy(clave = clave)
    }
}