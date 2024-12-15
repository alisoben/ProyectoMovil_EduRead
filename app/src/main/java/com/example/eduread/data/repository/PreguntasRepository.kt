package com.example.eduread.data.repository

import com.example.eduread.data.model.Pregunta
import com.example.eduread.data.model.response.PreguntasResponse
import com.example.eduread.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreguntasRepository {
    fun obtenerPreguntas(idCuento: Int, callback: (List<Pregunta>?) -> Unit) {
        RetrofitClient.apiService.obtenerPreguntas(idCuento).enqueue(object : Callback<PreguntasResponse> {
            override fun onResponse(call: Call<PreguntasResponse>, response: Response<PreguntasResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(response.body()?.data)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<PreguntasResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}
