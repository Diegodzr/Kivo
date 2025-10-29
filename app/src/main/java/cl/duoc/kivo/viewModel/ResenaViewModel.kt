package cl.duoc.kivo.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.ResenaModel
import cl.duoc.kivo.repository.ResenaRepository

class ResenaViewModel : ViewModel() {

    var resenas = mutableStateListOf<ResenaModel>()
        private set

    // llamada simple con usuario + comentario
    fun agregarResena(usuario: String, comentario: String) {
        if (comentario.isNotBlank()) {
            resenas.add(ResenaModel(usuario = usuario, comentario = comentario))
        }
    }
    fun cargarResenasIniciales() {
        if (resenas.isEmpty()) {
            resenas.addAll(ResenaRepository.getResenas())
        }
    }

}
