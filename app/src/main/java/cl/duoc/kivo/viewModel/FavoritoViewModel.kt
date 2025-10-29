package cl.duoc.kivo.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cl.duoc.kivo.model.FavoritoModel
import cl.duoc.kivo.repository.FavoritoRepository

class FavoritoViewModel : ViewModel() {
    var favoritos = mutableStateListOf<FavoritoModel>().apply {
        addAll(FavoritoRepository.getFavoritos())
    }

    fun agregarFavorito(titulo: String, descripcion: String) {
        val fav = FavoritoModel(titulo, descripcion)
        FavoritoRepository.agregarFavorito(fav)
        favoritos.add(fav)
    }
}

