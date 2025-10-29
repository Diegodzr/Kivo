package cl.duoc.kivo.repository

import cl.duoc.kivo.model.ResenaModel

object ResenaRepository {

    private val listaResenas = mutableListOf(
        ResenaModel("Ana", "Excelente explicación, muy clara y fácil de seguir. " +
                "Me encantó cómo cada tema se presenta paso a paso, sin abrumar. Realmente sentí que estaba aprendiendo " +
                "a mi propio ritmo y entendiendo todo desde la base."),
        ResenaModel("Carlos", "Me ayudó mucho a entender los conceptos básicos del lenguaje de señas. " +
                "Siempre había querido aprender, pero no encontraba recursos tan accesibles y bien organizados. Esta app " +
                "definitivamente me está dando una gran primera experiencia."),
        ResenaModel("María", "Buen ritmo y contenido interesante. Las lecciones son dinámicas y explican " +
                "justo lo necesario para que puedas avanzar sin sentirte perdido. Además, la interfaz es muy amigable, lo que " +
                "hace que el aprendizaje sea más motivador.")
    )

    fun getResenas(): List<ResenaModel> = listaResenas

    fun agregarResena(resena: ResenaModel) {
        listaResenas.add(resena)
    }
}