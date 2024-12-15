package com.example.eduread.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.R
import com.example.eduread.data.model.Pregunta
import com.example.eduread.ui.adapter.PreguntasAdapter
import com.example.eduread.data.repository.CuentoRepository
import com.example.eduread.data.repository.PreguntasRepository

class PreguntasActivity : AppCompatActivity() {
    private val preguntasRepository = PreguntasRepository()
    private lateinit var preguntasAdapter: PreguntasAdapter

    private val respuestasSeleccionadas = mutableMapOf<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)


        val cuentoId = intent.getIntExtra("cuento_id", -1)
        cargarPreguntas(cuentoId)

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            guardarRespuestas(cuentoId)
        }
    }
    private fun cargarPreguntas(cuentoId: Int) {
        preguntasRepository.obtenerPreguntas(cuentoId) { preguntas ->
            if (preguntas != null) {
                Log.d("PreguntasActivity", "Preguntas obtenidas: $preguntas")
                inicializarRecyclerView(preguntas)
            } else {
                Toast.makeText(this, "No se pudieron cargar las preguntas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inicializarRecyclerView(preguntas: List<Pregunta>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPreguntas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        preguntasAdapter = PreguntasAdapter(preguntas) { idPregunta, respuesta ->
            respuestasSeleccionadas[idPregunta] = respuesta
            Log.d("PreguntasActivity", "Pregunta $idPregunta respuesta seleccionada: $respuesta")
        }
        recyclerView.adapter = preguntasAdapter
    }
    private fun guardarRespuestas(idCuento: Int) {
        if (respuestasSeleccionadas.size < preguntasAdapter.itemCount) {
            Toast.makeText(this, "Responde todas las preguntas antes de guardar.", Toast.LENGTH_SHORT).show()
            return
        }

        val arrayRespuestas = respuestasSeleccionadas.values.joinToString(", ")
        // Llamar al repositorio para enviar las respuestas
        val cuentoRepository = CuentoRepository()
        cuentoRepository.reponderPreguntasCuento(
            id_usuario = 4,
            id_cuento = idCuento,
            array_respuestas = arrayRespuestas
        ) { response ->
            if (response?.status == 200) {
                Toast.makeText(this, "Respuestas enviadas con éxito.", Toast.LENGTH_SHORT).show()
                Log.d("PreguntasActivity", "Respuesta del servidor: ${response}")
            } else {
                Toast.makeText(this, "Error al enviar las respuestas.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}