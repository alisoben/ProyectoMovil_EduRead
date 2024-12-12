package com.example.eduread.data.model.response



data class ResponderResponse (
    val message: String,
    val status: Int,
    val data: Estrella
)

data class Estrella(
    val estrellas_obtenidas: Int
)

