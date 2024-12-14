package com.example.eduread.ui.manager

import android.content.Context
import com.example.eduread.network.RetrofitClient
import com.example.eduread.data.model.request.RegistrarUsuarioRequest
import com.example.eduread.data.model.response.RegistrarUsuarioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterManager {
    companion object {
        fun register(context: Context, nombre: String, clave: String, edad: Int, callback: (Boolean, String) -> Unit) {
            val registrarRequest = RegistrarUsuarioRequest(nombre, clave, edad)

            val call = RetrofitClient.apiService.registrar(registrarRequest)
            call.enqueue(object : Callback<RegistrarUsuarioResponse> {
                override fun onResponse(call: Call<RegistrarUsuarioResponse>, response: Response<RegistrarUsuarioResponse>) {
                    if (response.isSuccessful) {
                        val registrarResponse = response.body()
                        if (registrarResponse != null && registrarResponse.status == 200) {
                            callback(true, registrarResponse.message)
                        } else {
                            callback(false, registrarResponse?.message ?: "Error desconocido")
                        }
                    } else {
                        callback(false, "Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<RegistrarUsuarioResponse>, t: Throwable) {
                    callback(false, "Error de red: ${t.message}")
                }
            })
        }
    }
}