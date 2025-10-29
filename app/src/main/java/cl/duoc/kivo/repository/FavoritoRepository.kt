package cl.duoc.kivo.repository

import cl.duoc.kivo.model.FavoritoModel

object FavoritoRepository {
    private val listaFavoritos = mutableListOf(
        FavoritoModel("Hola", "Seña para saludar"),
        FavoritoModel("Gracias", "Seña para agradecer"),
        FavoritoModel("Adiós", "Seña para despedirse")
    )

    fun getFavoritos(): List<FavoritoModel> = listaFavoritos

    fun agregarFavorito(fav: FavoritoModel) {
        listaFavoritos.add(fav)
    }
}
