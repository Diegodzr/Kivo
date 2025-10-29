package cl.duoc.kivo.repository

import cl.duoc.kivo.R
import cl.duoc.kivo.model.LeccionModel

class LeccionRepository {
    fun getLecciones(): List<LeccionModel> {
        return listOf(
            LeccionModel(
                "Lección 1: Introducción al Lenguaje de Señas",
                "Nivel Principiante",
                "En esta lección aprenderás la historia del lenguaje de señas, su importancia en la inclusión y las normas básicas para comunicarte de manera respetuosa con la comunidad sorda.",
                R.drawable.leccion_1
            ),
            LeccionModel(
                "Lección 2: El Alfabeto Manual",
                "Nivel Inicial",
                "Aprende a representar cada letra del abecedario con tus manos. Esta base es fundamental para deletrear nombres y palabras que no tienen un signo propio.",
                R.drawable.leccion_2
            ),
            LeccionModel(
                "Lección 3: Expresiones y Emociones",
                "Nivel Intermedio",
                "Descubre cómo las expresiones faciales y el lenguaje corporal refuerzan el significado de las señas.",
                R.drawable.leccion_3
            ),
            LeccionModel(
                "Lección 4: Vocabulario Básico Diario",
                "Nivel Avanzado",
                "Aquí practicaremos señas relacionadas con la vida cotidiana: saludos, alimentos, transporte y más.",
                R.drawable.leccion_4
            ),
            LeccionModel(
                "Lección 5: Frases y Conversaciones Cortas",
                "Nivel Fluido",
                "Combina todo lo aprendido para construir frases y mantener diálogos breves.",
                R.drawable.leccion_5
            )
        )
    }
}
