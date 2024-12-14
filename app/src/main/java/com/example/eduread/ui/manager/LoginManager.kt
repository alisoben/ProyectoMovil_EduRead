package com.example.eduread.ui.manager

import android.content.Context
import com.example.eduread.network.RetrofitClient
import com.example.eduread.data.model.request.LoginRequest
import com.example.eduread.data.model.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginManager {
    companion object {
        fun login(context: Context, nombre: String, clave: String, callback: (Boolean, String, Int?) -> Unit) {
            val loginRequest = LoginRequest(nombre, clave)

            val call = RetrofitClient.apiService.login(loginRequest)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null && loginResponse.status == 200) {
                            callback(true, "Bienvenido", loginResponse.data.id_usuario)
                        } else {
                            callback(false, loginResponse?.message ?: "Error desconocido", null)
                        }
                    } else {
                        callback(false, "Error: ${response.message()}", null)
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    callback(false, "Error de red: ${t.message}", null)
                }
            })
        }
    }
}
