package cl.duoc.kivo.viewModel

import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.LeccionModel
import cl.duoc.kivo.repository.LeccionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LeccionViewModel : ViewModel() {

    private val repository = LeccionRepository()

    private val _lecciones = MutableStateFlow<List<LeccionModel>>(emptyList())
    val lecciones: StateFlow<List<LeccionModel>> = _lecciones

    private val _expandedState = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val expandedState: StateFlow<Map<Int, Boolean>> = _expandedState

    init {
        _lecciones.value = repository.getLecciones()
    }

    fun toggleExpansion(index: Int) {
        _expandedState.value = _expandedState.value.toMutableMap().apply {
            put(index, !(_expandedState.value[index] ?: false))
        }
    }
}
