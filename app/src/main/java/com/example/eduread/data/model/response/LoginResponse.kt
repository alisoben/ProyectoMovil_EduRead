package com.example.eduread.data.model.response

import com.example.eduread.data.model.Usuario

data class LoginResponse (
    val message: String,
    val status: Int,
    val data: Usuario
)


