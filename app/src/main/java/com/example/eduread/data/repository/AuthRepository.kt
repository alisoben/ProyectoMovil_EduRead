package com.example.eduread.data.repository


import com.example.eduread.data.model.request.LoginRequest
import com.example.eduread.data.model.request.RegistrarUsuarioRequest
import com.example.eduread.data.model.response.LoginResponse
import com.example.eduread.data.model.response.RegistrarUsuarioResponse
import com.example.eduread.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    fun registrarUsuario(nombre: String, clave: String, edad: Int, callback: (RegistrarUsuarioResponse?) -> Unit) {
        val registrarUsuarioRequest = RegistrarUsuarioRequest(
            nombre = nombre,
            clave = clave,
            edad = edad
        )

        RetrofitClient.apiService.registrar(registrarUsuarioRequest).enqueue(object : Callback<RegistrarUsuarioResponse> {
            override fun onResponse(call: Call<RegistrarUsuarioResponse>, response: Response<RegistrarUsuarioResponse>) {
                // Verifica si la respuesta fue exitosa
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    // En caso de que la respuesta no sea exitosa, puedes manejar el error aquí
                    callback(null)
                }
            }

            override fun onFailure(call: Call<RegistrarUsuarioResponse>, t: Throwable) {
                // En caso de error en la comunicación de red
                callback(null)
            }
        })
    }
    fun loginUsuario(nombre: String, clave: String, callback: (LoginResponse?) -> Unit) {
        val loginRequest = LoginRequest(nombre=nombre,clave=clave)
        RetrofitClient.apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback(response.body())
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

}