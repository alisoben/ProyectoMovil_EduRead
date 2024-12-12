package com.example.eduread.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.R
import com.example.eduread.data.request.Libro
import com.example.eduread.ui.view.CuentosActivity

class CardAdapter(
    private val cardList: List<Libro>,
    private val listener: OnCardClickListener
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.imageView.setImageResource(card.image01)
        holder.titleTextView.text = card.title

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context // Obtenemos el contexto desde el holder
            val intent = Intent(context, CuentosActivity::class.java).apply {
                putExtra("card_title", card.title)
                putExtra("card_text", card.text) // Pasar el texto del card
                putExtra("card_image", card.image01)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = cardList.size
}

interface OnCardClickListener {
    fun onCardClick(card: Libro)
}
