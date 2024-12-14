package com.example.eduread.data.model.response
import com.example.eduread.data.model.Cuento

data class CuentosResponse (
    val message: String,
    val status: Int,
    val data: ArrayList<Cuento>
)