package com.example.eduread.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduread.R
import com.example.eduread.data.model.Cuento
import com.example.eduread.ui.adapter.OnCardClickListener
import com.example.eduread.ui.manager.CuentosManager

class CuentosActivity : AppCompatActivity(), OnCardClickListener {

    private lateinit var recyclerView: RecyclerView

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
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val userId = intent.getIntExtra("usuario_id", 1)
        Toast.makeText(this, "Entrando con ID de usuario: $userId", Toast.LENGTH_SHORT).show()
        if (userId == -1) {
            Toast.makeText(this, "ID no válido, usando ID predeterminado (1)", Toast.LENGTH_SHORT).show()
        }
        CuentosManager.fetchCuentosData(userId, this)
    }

    override fun onCardClick(card: Cuento) {
        val intent = Intent(this, LecturaActivity::class.java).apply {
            putExtra("card_title", card.title)
            putExtra("card_text", card.text)
            putExtra("card_image", card.image)
        }
        startActivity(intent)
    }
}