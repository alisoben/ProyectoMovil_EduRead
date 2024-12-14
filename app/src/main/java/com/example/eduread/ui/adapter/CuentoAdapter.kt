package com.example.eduread.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.R
import com.example.eduread.data.model.Cuento

class CardAdapter(
    private val cuentos: List<Cuento>,
    private val listener: OnCardClickListener
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val starsContainer: ViewGroup = itemView.findViewById(R.id.item_stars)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cuento = cuentos[position]
        val context: Context = holder.itemView.context
        val imageName = cuento.image
        val imageNameWithoutExtension = imageName.substringBeforeLast(".")
        val imageResId = context.resources.getIdentifier(imageNameWithoutExtension, "drawable", context.packageName)
        if (imageResId != 0) {
            holder.imageView.setImageResource(imageResId)
        } else {
            holder.imageView.setImageResource(R.drawable.img00)
        }
        holder.titleTextView.text = cuento.title
        val starsCount = cuento.estrella
        for (i in 0 until holder.starsContainer.childCount) {
            val starView = holder.starsContainer.getChildAt(i) as ImageView
            if (i < starsCount) {
                starView.setImageResource(R.drawable.star_icon)
            } else {
                starView.setImageResource(R.drawable.star_icon_gray)
            }
        }
        holder.itemView.setOnClickListener {
            listener.onCardClick(cuento)
        }
    }

    override fun getItemCount(): Int = cuentos.size
}

interface OnCardClickListener {
    fun onCardClick(card: Cuento)
}
