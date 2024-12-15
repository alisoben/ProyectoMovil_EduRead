package com.example.eduread.data.model.response

import com.example.eduread.data.model.Pregunta

data class PreguntasResponse(
    val data: List<Pregunta>,
    val message: String,
    val status: Int
)