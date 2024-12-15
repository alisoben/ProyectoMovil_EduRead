package com.example.eduread.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.ui.adapter.CardAdapter
import com.example.eduread.data.model.request.Libro
import com.example.eduread.R
import com.example.eduread.data.model.request.CardDataProvider
import com.example.eduread.ui.adapter.OnCardClickListener

class CuentosActivity : AppCompatActivity(), OnCardClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuentos2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cardList = CardDataProvider.getSampleData()
        recyclerView.adapter = CardAdapter(cardList, this)
    }
    override fun onCardClick(card: Libro) {
        Log.d("CuentosActivity", "Cuento seleccionado: ${card.title}")
        val intent = Intent(this, PreguntasActivity::class.java)
        intent.putExtra("cuento_id", card.idLib)
        startActivity(intent)
    }
}