package cl.duoc.kivo.model

data class RegisterModel(
    var nombre: String = "",
    var correo: String = "",
    var edad: String = "",
    var terminos: Boolean = false
)