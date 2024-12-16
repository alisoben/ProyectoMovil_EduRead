package com.example.eduread.ui.manager

import android.content.Context
import com.example.eduread.network.RetrofitClient
import com.example.eduread.data.model.request.LoginRequest
import com.example.eduread.data.model.response.LoginResponse
import com.example.eduread.ui.estatico.UsuarioStatic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class LoginManager {
    companion object {
        fun login(context: Context, nombre: String, clave: String, callback: (Boolean, String, Int,Int) -> Unit) {
            val loginRequest = LoginRequest(nombre, clave)

            val call = RetrofitClient.apiService.login(loginRequest)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null && loginResponse.status == 200) {
                            UsuarioStatic.id_usuario_static= loginResponse.data.id_usuario
                            val idUsuario = loginResponse.data.id_usuario
                            val edad = loginResponse.data.edad
                            callback(true, "Bienvenido", idUsuario, edad)
                        } else {
                            callback(false, loginResponse?.message ?: "Error desconocido",0,0)
                        }
                    } else {
                        callback(false, "Error: ${response.message()}",0,0)
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    callback(false, "Error de red: ${t.message}",0,0)
                }
            })
        }
    }
}