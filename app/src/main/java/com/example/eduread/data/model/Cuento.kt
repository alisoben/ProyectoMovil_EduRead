package com.example.eduread.data.model

data class Cuento(
    val estrella: Int,
    val id_cuento: Int,
    val title: String,
    val text: String,
    val image: String,
    val preguntas: ArrayList<Pregunta>
) {
}