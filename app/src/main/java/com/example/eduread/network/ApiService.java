package com.example.eduread.network;



import com.example.eduread.data.model.request.RegistrarUsuarioRequest;
import com.example.eduread.data.model.response.RegistrarUsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface ApiService {
    @POST("register/usuario")
    fun registrarUsuario(@Body registrarUsuarioRequest:RegistrarUsuarioRequest): Call<RegistrarUsuarioResponse>
    @POST

}