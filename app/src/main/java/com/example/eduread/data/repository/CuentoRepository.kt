package com.example.eduread.data.repository

import com.example.eduread.data.model.request.ResponderRequest
import com.example.eduread.data.model.response.CuentosResponse
import com.example.eduread.data.model.response.ResponderResponse
import com.example.eduread.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuentoRepository {
    fun getAllCuentos(id_usuario: Int,callback: (CuentosResponse?) -> Unit) {
        RetrofitClient.apiService.listarCuentos(id_usuario).enqueue(object :
            Callback<CuentosResponse> {
            override fun onResponse(
                call: Call<CuentosResponse>,
                response: Response<CuentosResponse>
            ) {
                callback(response.body())
            }

            override fun onFailure(call: Call<CuentosResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun reponderPreguntasCuento(id_usuario: Int,id_cuento:Int,array_respuestas: String,callback: (ResponderResponse?) -> Unit) {
        val responderRequest = ResponderRequest(
            id_usuario = id_usuario,
            id_cuento = id_cuento,
            array_respuestas = array_respuestas
        )

        RetrofitClient.apiService.RespuestaPreguntasCuento(responderRequest).enqueue(object : Callback<ResponderResponse> {
            override fun onResponse(call: Call<ResponderResponse>, response: Response<ResponderResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ResponderResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}