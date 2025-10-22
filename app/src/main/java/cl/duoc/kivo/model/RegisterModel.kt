package cl.duoc.kivo.model

data class UsuarioRegister(
    var nombre: String = "",
    var correo: String = "",
    var edad: String = "",
    var terminos: Boolean = false
)