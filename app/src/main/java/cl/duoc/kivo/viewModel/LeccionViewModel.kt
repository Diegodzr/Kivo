package cl.duoc.kivo.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.LeccionModel
import cl.duoc.kivo.repository.LeccionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LeccionViewModel : ViewModel() {
    private val repository = LeccionRepository()

    // Lista de lecciones observable
    private val _lecciones = MutableStateFlow(repository.getLecciones())
    val lecciones: StateFlow<List<LeccionModel>> get() = _lecciones

    // Estado de expansión de cada lección (true si está expandida)
    private val _expandedState = mutableStateListOf<Boolean>().apply {
        repeat(_lecciones.value.size) { add(false) }
    }
    val expandedState: List<Boolean> get() = _expandedState

    // Cambiar estado de expansión
    fun toggleExpansion(index: Int) {
        _expandedState[index] = !_expandedState[index]
    }
}
