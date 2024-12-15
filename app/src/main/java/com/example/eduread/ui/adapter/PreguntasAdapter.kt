package com.example.eduread.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.R
import com.example.eduread.data.model.Pregunta

class PreguntasAdapter(
    private val preguntas: List<Pregunta>,
    private val onRespuestaSeleccionada: (Int, String) -> Unit ) :
    RecyclerView.Adapter<PreguntasAdapter.PreguntaViewHolder>() {

    class PreguntaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val encabezadoPregunta: TextView = itemView.findViewById(R.id.encabezadoPregunta)
        val preguntaTexto: TextView = itemView.findViewById(R.id.preguntaTexto)
        val btnSi: TextView  = itemView.findViewById(R.id.btnSi)
        val btnNo: TextView  = itemView.findViewById(R.id.btnNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pregunta_item, parent, false)
        return PreguntaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreguntaViewHolder, position: Int) {
        val pregunta = preguntas[position]
        holder.encabezadoPregunta.text = "Pregunta ${position + 1}"
        holder.preguntaTexto.text = pregunta.contenido

        holder.btnSi.setOnClickListener {
            onRespuestaSeleccionada(pregunta.id_pregunta, "sí")
            holder.btnSi.setBackgroundResource(R.drawable.background_pregunta_opciones_seleccionada)
            holder.btnNo.setBackgroundResource(R.drawable.background_pregunta_opciones)
            holder.btnSi.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.btnNo.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.md_theme_onSecondaryContainer))
        }

        holder.btnNo.setOnClickListener {
            onRespuestaSeleccionada(pregunta.id_pregunta, "no")
            holder.btnNo.setBackgroundResource(R.drawable.background_pregunta_opciones_seleccionada)
            holder.btnSi.setBackgroundResource(R.drawable.background_pregunta_opciones)
            holder.btnNo.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.btnSi.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.md_theme_onSecondaryContainer))
        }
    }


    override fun getItemCount(): Int = preguntas.size
}