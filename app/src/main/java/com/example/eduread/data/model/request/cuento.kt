package com.example.eduread.data.model.request

import com.example.eduread.R

data class Libro(
    val idLib: Int,
    val title: String,
    val text: String,
    val image01: Int
)

object CardDataProvider {
    fun getSampleData(): List<Libro> {
        return listOf(
            Libro(0, "Caperucita Roja", "Había una vez una niña llamada Caperucita Roja que vivía en una aldea cerca del bosque. Siempre llevaba una capa con capucha de color rojo, por eso la llamaban así. Un día, su madre le pidió que llevara una canasta con comida a su abuelita, que estaba enferma y vivía en una casita al otro lado del bosque.\n" +
                    "Antes de salir, su madre le advirtió:\n" +
                    "👩🏻:No hables con extraños y no te salgas del camino.\n" +
                    "Caperucita Roja prometió obedecer y partió felizmente hacia la casa de su abuela. Mientras caminaba, un lobo apareció en su camino. Era un animal grande y astuto, pero fingió ser amable.\n" +
                    "🐺:Hola, pequeña. ¿A dónde vas tan temprano? —preguntó el lobo.\n" +
                    "👧🏻:Voy a casa de mi abuelita para llevarle esta canasta de comida —contestó Caperucita, sin sospechar nada.\n" +
                    "El lobo, muy listo, pensó en un plan. Corrió rápidamente por un atajo para llegar antes que Caperucita a la casa de la abuela. Cuando llegó, engañó a la anciana, la encerró en un armario y se disfrazó con su ropa. Luego se metió en la cama para esperar a Caperucita.\n" +
                    "Cuando la niña llegó, notó que su abuela se veía extraña.\n" +
                    "👧🏻:Abuelita, ¡qué orejas tan grandes tienes!\n" +
                    "👵🏻-🐺:Son para oírte mejor, querida —respondió el lobo con voz fingida.\n" +
                    "👧🏻:¡Y qué ojos tan grandes tienes!\n" +
                    "👵🏻-🐺:Son para verte mejor.\n" +
                    "👧🏻:¡Y qué dientes tan grandes tienes!\n" +
                    "🐺¡Son para comerte mejor! gritó el lobo, saltando de la cama.\n" +
                    "Caperucita gritó pidiendo ayuda. Justo entonces, un leñador que pasaba por ahí escuchó sus gritos y corrió a la casa. Con su hacha, espantó al lobo, que salió corriendo y nunca volvió.\n" +
                    "Caperucita y su abuelita se abrazaron, agradecidas por haber sido rescatadas. Desde ese día, Caperucita aprendió a no hablar con extraños y a obedecer siempre los consejos de su madre.\n" +
                    "Fin.\n" +
                    ".", R.drawable.img00),
            Libro(1, "Ricitos de Oro", "text2", R.drawable.img10),
            Libro(2, "Los Tres Cerditos", "text3", R.drawable.img20),
            Libro(3, "Hansel y Gretel", "text4", R.drawable.img30),
            Libro(4, "El Patito Feo", "text5", R.drawable.img40),
            Libro(5, "Blancanieves", "text6", R.drawable.img50),
            Libro(6, "La Cenicienta", "text7", R.drawable.img60),
            Libro(7, "El Gato con Botas", "text8", R.drawable.img70),
            Libro(8, "La Liebre y la Tortuga", "text9", R.drawable.img80),
            Libro(9, "Rapunzel", "text10", R.drawable.img90)
        )
    }
}
