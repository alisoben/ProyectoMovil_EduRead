package com.example.eduread.ui.manager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.R
import com.example.eduread.network.RetrofitClient
import com.example.eduread.data.model.response.CuentosResponse
import com.example.eduread.ui.adapter.CardAdapter
import com.example.eduread.ui.view.CuentosActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuentosManager {
    companion object {
        fun fetchCuentosData(idUsuario: Int, context: Context) {
            val call = RetrofitClient.apiService.listarCuentos(idUsuario)
            call.enqueue(object : Callback<CuentosResponse> {
                override fun onResponse(call: Call<CuentosResponse>, response: Response<CuentosResponse>) {
                    if (response.isSuccessful) {
                        val cuentosResponse = response.body()
                        if (cuentosResponse != null && cuentosResponse.status == 200) {
                            val cuentos = cuentosResponse.data

                            val recyclerView = (context as CuentosActivity).findViewById<RecyclerView>(
                                R.id.recyclerView)
                            recyclerView.adapter = CardAdapter(cuentos, context)
                        } else {
                            Toast.makeText(context, cuentosResponse?.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("CuentosManager", "Error en respuesta: ${response.message()}")
                        Toast.makeText(context, "Error al obtener cuentos: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CuentosResponse>, t: Throwable) {
                    Log.e("CuentosManager", "Error de red: ${t.message}", t)
                    Toast.makeText(context, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}