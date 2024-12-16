package com.example.eduread.data.model.response

import com.example.eduread.data.model.Usuario

data class UsuarioResponse(
    val message: String,
    val status: Int,
    val data: Usuario
) {

}